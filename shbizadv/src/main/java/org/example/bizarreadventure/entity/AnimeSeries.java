package org.example.bizarreadventure.entity;

import jakarta.persistence.*;

@Entity
@Table(name="animeseries")
public class AnimeSeries {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int animeseries_id;
    int animeid;
    int number;
    String video;

    public int getAnimeseries_id() {
        return animeseries_id;
    }

    public void setAnimeseries_id(int animeseries_id) {
        this.animeseries_id = animeseries_id;
    }

    public int getAnimeid() {
        return animeid;
    }

    public void setAnimeid(int anime_id) {
        this.animeid = anime_id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
}
