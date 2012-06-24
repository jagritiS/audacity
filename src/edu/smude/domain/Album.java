package edu.smude.domain;

import edu.smude.services.BandService;

import java.util.LinkedList;
import java.util.List;

public class Album {

    private BandService bandService = new BandService();

    private long id;
    private String name;
    private String details; 
    private long bandId;
    private List<Artist> artists = new LinkedList<Artist>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Band getBand() {
        return bandService.get(bandId);
    }

    public long getBandId() {
        return bandId;
    }

    public void setBandId(long bandId) {
        this.bandId = bandId;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }
}
