package edu.smude.controllers;

import edu.smude.domain.Album;
import edu.smude.domain.Band;
import edu.smude.domain.Song;
import edu.smude.services.AlbumService;
import edu.smude.services.ArtistService;
import edu.smude.services.BandService;
import edu.smude.services.SongService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class UserController extends HttpServlet {
    private AlbumService albumService = new AlbumService();
    private ArtistService artistService = new ArtistService();
    private BandService bandService = new BandService();
    private SongService songService = new SongService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }


    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String module = request.getParameter("module") == null ? "profile" : request.getParameter("module");


        if(module.equals("profile")){
            profile(request, response);
        } else if (module.equals("songs")) {
            songs(request, response);
        } else if (module.equals("albums")) {
            albums(request, response);
        } else if (module.equals("reports")) {
            reports(request, response);
        }

    }

    protected void profile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "show" : request.getParameter("action");
        HttpSession session = request.getSession();
        if(action.equals("update")) {
            Band band = new Band();
            try{
                BeanUtils.populate(band, request.getParameterMap());
            }catch(Exception e){
                e.printStackTrace();
            }
            if(band.getId() == 0 ){ //new band profile
                band.setUserId((Long)session.getAttribute("userId"));
                bandService.add(band);
            } else {
                bandService.update(band);
            }
            response.sendRedirect("user?module=profile");
            return;
        } else {
            Band band = bandService.findByUserId((Long)session.getAttribute("userId"));
            request.setAttribute("band",band );
            RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/user/profile.jsp");
            view.forward(request, response);
        }

    }

    protected void songs(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Long userId = (Long)session.getAttribute("userId");
        Band band = bandService.findByUserId(userId);
        String action = request.getParameter("action") == null ? "list" : request.getParameter("action");
        
        if(ServletFileUpload.isMultipartContent(request)){
        	action = "add";
        }

        if(action.equals("list")){
        	System.out.println("action: list");
            List<Song> songs = songService.findByBandId(band.getId());
            request.setAttribute("songs", songs);
            request.setAttribute("albums", albumService.findByUserId(userId));
            RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/user/songs.jsp");
            view.forward(request, response);
        } else if (action.equals("add")) {
            System.out.println("action: add");
            Song song = buildSong(request);
            songService.add(song);
            response.sendRedirect("user?module=songs&action=list");
        } else if(action.equals("delete")) {
            Long id = Long.parseLong(request.getParameter("id"));
            songService.delete(id);
        }

    }

    protected void albums(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Long userId = (Long)session.getAttribute("userId");
        Band band = bandService.findByUserId(userId);
        String action = request.getParameter("action") == null ? "list" : request.getParameter("action");

        if(ServletFileUpload.isMultipartContent(request)){
            action = "add";
        }

        if(action.equals("list")) {
            request.setAttribute("albums", albumService.findByUserId(userId));
            RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/user/albums.jsp");
            view.forward(request, response);
        } else if (action.equals("add")) {
            try{
                Album album = buildAlbum(request);

                album.setBandId(band.getId());
                albumService.add(album);
            }catch(Exception e){
                e.printStackTrace();
            }
            response.sendRedirect("user?module=albums&action=list");
        } else if (action.equals("delete")) {
            Long albumId = Long.parseLong(request.getParameter("id"));
            albumService.delete(albumId);
            response.sendRedirect("user?module=albums&action=list");
        }



    }

    protected void reports(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/user/profile.jsp");
        view.forward(request, response);
    }

    private Song buildSong(HttpServletRequest request){
        Song song = new Song();
        try{

            Map<String,String> parameters = new HashMap<String,String>();

            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            String uploadPath = request.getSession().getServletContext().getRealPath("/songs/");
            System.out.println("uploadPath: " + uploadPath);
            String filename = "";


            List<FileItem> items = upload.parseRequest(request);

            for(FileItem item: items){
                if(item.isFormField()){
                    //build bean properties param
                    parameters.put(item.getFieldName(), item.getString());
                }else {

                    filename = System.currentTimeMillis()+FilenameUtils.getName(item.getName());


                    File f = new File(uploadPath+"/"+filename);

                    InputStream fileContent = item.getInputStream();
                    OutputStream out = new FileOutputStream(f);

                    byte buf[] = new byte[1024];
                    int len;
                    while((len=fileContent.read(buf))>0){
                        out.write(buf,0,len);
                    }
                    fileContent.close();
                    out.flush();
                    out.close();
                }
            }


            BeanUtils.populate(song, parameters);
            song.setFileName(filename);


        }catch(Exception e){
            e.printStackTrace();
        }
        return song;
    }

    private Album buildAlbum(HttpServletRequest request){
        Album album = new Album();
        try{

            Map<String,String> parameters = new HashMap<String,String>();

            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            String uploadPath = request.getSession().getServletContext().getRealPath("/albumcovers/");
            System.out.println("uploadPath: " + uploadPath);
            String filename = "";


            List<FileItem> items = upload.parseRequest(request);

            for(FileItem item: items){
                if(item.isFormField()){
                    //build bean properties param
                    parameters.put(item.getFieldName(), item.getString());
                }else {

                    filename = System.currentTimeMillis()+FilenameUtils.getName(item.getName());


                    File f = new File(uploadPath+"/"+filename);

                    InputStream fileContent = item.getInputStream();
                    OutputStream out = new FileOutputStream(f);

                    byte buf[] = new byte[1024];
                    int len;
                    while((len=fileContent.read(buf))>0){
                        out.write(buf,0,len);
                    }
                    fileContent.close();
                    out.flush();
                    out.close();
                }
            }


            BeanUtils.populate(album, parameters);
            album.setFileName(filename);


        }catch(Exception e){
            e.printStackTrace();
        }
        return album;
    }
}
