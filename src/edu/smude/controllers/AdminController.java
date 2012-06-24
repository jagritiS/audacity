package edu.smude.controllers;

import edu.smude.services.AlbumService;
import edu.smude.services.ArtistService;
import edu.smude.services.BandService;
import edu.smude.services.SongService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminController extends HttpServlet {
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
        String action = request.getParameter("action") == null ? "" : request.getParameter("action");

        
    }
}
