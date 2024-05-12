package org.example.bizarreadventure.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "genre")
public class Genre{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int genre_id;
    private String name;

    public int getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(int genre_id) {
        this.genre_id = genre_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}