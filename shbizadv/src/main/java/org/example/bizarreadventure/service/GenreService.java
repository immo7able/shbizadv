package org.example.bizarreadventure.service;

import org.example.bizarreadventure.entity.Anime;
import org.example.bizarreadventure.entity.AnimeGenre;
import org.example.bizarreadventure.entity.AnimeGenreDTO;
import org.example.bizarreadventure.entity.Genre;
import org.example.bizarreadventure.repository.AnimeGenreRepository;
import org.example.bizarreadventure.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GenreService {
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private AnimeGenreRepository animeGenreRepository;
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    public Genre addGenre(String name) {
        Genre genre = new Genre();
        genre.setName(name);
        return genreRepository.save(genre);
    }
    public List<AnimeGenreDTO> getGenresForAnime(int animeId) {
        List<AnimeGenre> genres = animeGenreRepository.findByAnimeId(animeId);
        return genres.stream().map(ag -> {
            AnimeGenreDTO dto = new AnimeGenreDTO();
            dto.setName(ag.getGenre().getName());
            return dto;
        }).collect(Collectors.toList());
    }
    public Genre getGenre(int id){
        Optional<Genre> animeOptional = genreRepository.findById(id);
        return animeOptional.orElse(null);
    }
    public boolean isGenreExists(int id){
        return genreRepository.existsById(id);
    }
}
