package org.example.bizarreadventure.repository;


import org.example.bizarreadventure.entity.Anime;
import org.example.bizarreadventure.entity.User;
import org.example.bizarreadventure.entity.UserRating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRatingRepository extends JpaRepository<UserRating, Integer> {
    UserRating findByUserAndAnime(User user, Anime anime);
    List<UserRating> findByAnime(Anime anime);
}
