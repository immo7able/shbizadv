package org.example.bizarreadventure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.example.bizarreadventure.entity.AnimeGenre;

import java.util.List;


@Repository
public interface AnimeGenreRepository extends JpaRepository<AnimeGenre, Integer> {
        @Query("SELECT ag FROM AnimeGenre ag WHERE ag.anime.anime_id = :animeId")
        List<AnimeGenre> findByAnimeId(@Param("animeId") int animeId);
        @Query(value = "SELECT * FROM animegenre a WHERE a.genre_id= :genreId", nativeQuery = true)
        List<AnimeGenre> findAllByGenreId(int genreId);
}
