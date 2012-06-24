package edu.smude.domain;

import edu.smude.services.AlbumService;

import java.util.Date;

public class Song {

    private AlbumService albumService = new AlbumService();
    
    private long id;
    private String name;    
    private String details;
    private String fileName;
    private Date createdDate;

    private boolean featured;
    private Date featuredDate;
    
    private long albumId;

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

    public Album getAlbum() {
        return albumService.get(albumId);
    }

    public long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(long albumId) {
        this.albumId = albumId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public boolean isFeatured() {
        return featured;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }

    public Date getFeaturedDate() {
        return featuredDate;
    }

    public void setFeaturedDate(Date featuredDate) {
        this.featuredDate = featuredDate;
    }
}
