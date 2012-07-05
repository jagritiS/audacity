package edu.smude.controllers;

import edu.smude.domain.Album;
import edu.smude.domain.Artist;
import edu.smude.domain.Band;
import edu.smude.domain.Song;
import edu.smude.services.AlbumService;
import edu.smude.services.ArtistService;
import edu.smude.services.BandService;
import edu.smude.services.SongService;
import edu.smude.services.ViewCountService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class IndexController extends HttpServlet {
    private AlbumService albumService = new AlbumService();
    private ArtistService artistService = new ArtistService();
    private BandService bandService = new BandService();
    private SongService songService = new SongService();
    private ViewCountService logService = new ViewCountService();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }


    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "" : request.getParameter("action");

        if(action.equals("album")) {
            album(request, response);
        } else if (action.equals("artist")){
            artist(request, response);
        } else if (action.equals("band")){
            band(request, response);
        } else if (action.equals("song") || action.equals("play")){
            song(request, response);
        } else {
            
            request.setAttribute("topAlbums", albumService.topList());
            request.setAttribute("featuredBands", bandService.featured());
            request.setAttribute("topSongs", songService.topList());
            request.setAttribute("latestSongs", songService.latest());
            request.setAttribute("featuredSongs", songService.featured());

            RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/index/index.jsp");
            view.forward(request, response);
        }
    }

    protected void album(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = request.getParameter("id") == null ? 0 : Long.parseLong(request.getParameter("id"));
        Album album = albumService.get(id);
        request.setAttribute("album", album);
        request.setAttribute("songs", songService.findByAlbumId(id));
        
        logService.logAlbum(album);

        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/index/album.jsp");
        view.forward(request, response);
    }

    protected void artist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = request.getParameter("id") == null ? 0 : Long.parseLong(request.getParameter("id"));
        Artist artist = artistService.get(id);
        request.setAttribute("artist", artist);
        
        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/index/artist.jsp");
        view.forward(request, response);
    }

    protected void band(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = request.getParameter("id") == null ? 0 : Long.parseLong(request.getParameter("id"));
        Band band = bandService.get(id);
        request.setAttribute("band", band);
        request.setAttribute("songs",songService.findByBandId(id));
        
        logService.logBand(band);

        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/index/band.jsp");
        view.forward(request, response);
    }

    protected void song(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = request.getParameter("id") == null ? 0 : Long.parseLong(request.getParameter("id"));
        Song song;
        if(id == 0 ){
            song = songService.random();
        } else {
            song = songService.get(id);
        }

        request.setAttribute("song", song);
        
        logService.logSong(song);
        
        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/index/song.jsp");
        view.forward(request, response);
    }
}
