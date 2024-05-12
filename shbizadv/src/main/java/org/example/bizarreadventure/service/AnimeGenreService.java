package org.example.bizarreadventure.service;

import org.example.bizarreadventure.entity.Anime;
import org.example.bizarreadventure.entity.AnimeGenre;
import org.example.bizarreadventure.entity.AnimeGenreDTO;
import org.example.bizarreadventure.entity.Genre;
import org.example.bizarreadventure.repository.AnimeGenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnimeGenreService {
    @Autowired
    private AnimeGenreRepository animeGenreRepository;

    public AnimeGenre addAnimeGenre(int genreId, Anime anime) {
        Genre genre = new Genre();
        genre.setGenre_id(genreId);

        AnimeGenre animeGenre = new AnimeGenre();
        animeGenre.setAnime(anime);
        animeGenre.setGenre(genre);
        return animeGenreRepository.save(animeGenre);
    }

    public List<AnimeGenreDTO> getGenresForAnime(int animeId) {
        List<AnimeGenre> genres = animeGenreRepository.findByAnimeId(animeId);
        return genres.stream().map(ag -> {
            AnimeGenreDTO dto = new AnimeGenreDTO();
            dto.setName(ag.getGenre().getName());
            return dto;
        }).collect(Collectors.toList());
    }
    public List<Anime> getAnimeByGenre(int genreId){
        List<AnimeGenre> animeGenres = animeGenreRepository.findAllByGenreId(genreId);
        List<Anime> anime = new ArrayList<>();
        for(AnimeGenre el:animeGenres){
            anime.add(el.getAnime());
        }
        return anime;
    }
}
