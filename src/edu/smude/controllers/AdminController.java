package edu.smude.controllers;

import edu.smude.domain.Band;
import edu.smude.domain.Song;
import edu.smude.services.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdminController extends HttpServlet {
    private AlbumService albumService = new AlbumService();
    private ArtistService artistService = new ArtistService();
    private BandService bandService = new BandService();
    private SongService songService = new SongService();
    private UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }


    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("module") == null ? "users" : request.getParameter("module");

        if(action.equals("users")){
            users(request, response);
        } else if (action.equals("bands")){
            bands(request, response);
        } else if (action.equals("reports")){
            reports(request, response);
        }
    }

    protected void users(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "" : request.getParameter("action");

        if(action.equals("delete")){
            String uid = request.getParameter("id") == null ? "0" : request.getParameter("id");
            long id = Long.parseLong(uid);
            userService.delete(id);
        }

        request.setAttribute("users", userService.list());

        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/admin/users.jsp");
        view.forward(request, response);
    }

    protected void bands(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "list" : request.getParameter("action");

        if(action.equals("list")){
            request.setAttribute("bands", bandService.list());
            RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/admin/bands.jsp");
            view.forward(request, response);
        } else if (action.equals("detail")) {
            long id = Long.parseLong(request.getParameter("id"));
            Band band = bandService.get(id);
            List<Song> songs = songService.findByBandId(id);
            request.setAttribute("band", band);
            request.setAttribute("songs", songs);
            RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/admin/bandDetail.jsp");
            view.forward(request, response);
        } else if (action.equals("delete")) {
            long id = request.getParameter("id") == null ? 0 : Long.parseLong(request.getParameter("id"));
            bandService.delete(id);
            response.sendRedirect("admin?module=bands&action=list");
            return;
        } else if (action.equals("deleteSong")) {
            long id = request.getParameter("id") == null ? 0 : Long.parseLong(request.getParameter("id"));
            songService.delete(id);
            response.sendRedirect("admin?module=bands&action=list");
            return;
        }


    }

    protected void reports(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/admin/reports.jsp");
        view.forward(request, response);
    }

}
