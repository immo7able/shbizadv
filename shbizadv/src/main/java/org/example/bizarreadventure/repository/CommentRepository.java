package org.example.bizarreadventure.repository;
import org.example.bizarreadventure.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    Comment save(Comment comment);
    @Query("SELECT c FROM Comment c JOIN FETCH c.user u JOIN FETCH c.anime a WHERE c.anime.anime_id = :animeId")
    List<Comment> findByAnimeId(int animeId);
    @Query("SELECT c FROM Comment c WHERE c.user = :userId")
    List<Comment> findByAnime_UserId(int userId);

}
