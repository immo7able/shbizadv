package org.example.bizarreadventure.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Date;
@Entity
@Table(name="anime")
public class Anime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int anime_id;
    private String name;
    private int seriescount;
    private String status;
    private String studio;
    private String typeofanime;
    private String sourceofanime;
    private String avatar;
    private String background;
    private double rating;
    private int views;
    private Date outdate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeriesCount() {
        return seriescount;
    }

    public void setSeriesCount(int seriescount) {
        this.seriescount = seriescount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getTypeOfAnime() {
        return typeofanime;
    }

    public void setTypeOfAnime(String typeofanime) {
        this.typeofanime = typeofanime;
    }

    public String getSourceOfAnime() {
        return sourceofanime;
    }

    public void setSourceOfAnime(String sourceofanime) {
        this.sourceofanime = sourceofanime;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    @JsonProperty("animeId")
    public int getAnime_id() {
        return anime_id;
    }

    public void setAnime_id(int anime_id) {
        this.anime_id = anime_id;
    }

    public Date getOutdate() {
        return outdate;
    }

    public void setOutdate(Date outdate) {
        this.outdate = outdate;
    }

}
