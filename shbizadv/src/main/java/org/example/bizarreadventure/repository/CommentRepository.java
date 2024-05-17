package org.example.bizarreadventure.repository;
import org.example.bizarreadventure.entity.Comment;
import org.example.bizarreadventure.com.CommentStatus;
import org.example.bizarreadventure.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    Comment save(Comment comment);
    @Query("SELECT c FROM Comment c JOIN FETCH c.user u JOIN FETCH c.anime a WHERE c.anime.anime_id = :animeId")
    List<Comment> findByAnimeId(int animeId);
    List<Comment> findByStatus(CommentStatus status);

    @Query("SELECT c FROM Comment c WHERE c.user = :userId")
    List<Comment> findByAnime_UserId(User userId);

    @Query("SELECT c FROM Comment c WHERE c.status = 'PENDING'")
    List<Comment> findPendingComments();

    @Query("SELECT c FROM Comment c WHERE c.status = 'APPROVED'")
    List<Comment> findApprovedComments();

    @Query("SELECT c FROM Comment c WHERE c.status = 'REJECTED'")
    List<Comment> findRejectedComments();

    @Query("SELECT c FROM Comment c WHERE c.status = 'PENDING' AND c.user.user_id = :userId")
    List<Comment> findPendingCommentsByUser(@Param("userId") int userId);

    @Query("SELECT c FROM Comment c WHERE c.status = 'APPROVED' AND c.user.user_id = :userId")
    List<Comment> findApprovedCommentsByUser(@Param("userId") int userId);

    @Query("SELECT c FROM Comment c WHERE c.status = 'REJECTED' AND c.user.user_id = :userId")
    List<Comment> findRejectedCommentsByUser(@Param("userId") int userId);
}
