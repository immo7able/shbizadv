package org.example.bizarreadventure.service;

import org.example.bizarreadventure.entity.Anime;
import org.example.bizarreadventure.entity.User;
import org.example.bizarreadventure.entity.UserRating;
import org.example.bizarreadventure.repository.AnimeRepository;
import org.example.bizarreadventure.repository.UserRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRatingService {
    @Autowired
    private  UserRatingRepository userRatingRepository;
    @Autowired
    private  AnimeRepository animeRepository;

    @Autowired
    public UserRatingService(UserRatingRepository userRatingRepository, AnimeRepository animeRepository) {
        this.userRatingRepository = userRatingRepository;
        this.animeRepository = animeRepository;
    }

    public boolean rateAnime(User user, Anime anime, int rating) {
        UserRating existingRating = userRatingRepository.findByUserAndAnime(user, anime);

        if (existingRating != null) {
            existingRating.setRating(rating);
        } else {
            existingRating = new UserRating();
            existingRating.setUser(user);
            existingRating.setAnime(anime);
            existingRating.setRating(rating);
        }

        userRatingRepository.save(existingRating);

        updateAnimeRating(anime);
        return false;
    }

    private void updateAnimeRating(Anime anime) {
        double newRating = calculateAverageRating(anime);
        anime.setRating(newRating);
        animeRepository.save(anime);
    }

    private double calculateAverageRating(Anime anime) {
        List<UserRating> ratings = userRatingRepository.findByAnime(anime);
        double sum = 0;
        for (UserRating rating : ratings) {
            sum += rating.getRating();
        }
        return ratings.isEmpty() ? 0 : sum / ratings.size();
    }
    public Integer getUserRatingForAnime(User userId, Anime animeId) {
        UserRating userRating = userRatingRepository.findByUserAndAnime(userId, animeId);
        return userRating != null ? userRating.getRating() : null;
    }

}

