package org.example.bizarreadventure.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "animegenre")
public class AnimeGenre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int animegenre_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "anime_id")
    private Anime anime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id")
    private Genre genre;

    public int getId() {
        return animegenre_id;
    }

    public void setId(int animegenre_id) {
        this.animegenre_id = animegenre_id;
    }

    public Anime getAnime() {
        return anime;
    }

    public void setAnime(Anime anime) {
        this.anime = anime;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
