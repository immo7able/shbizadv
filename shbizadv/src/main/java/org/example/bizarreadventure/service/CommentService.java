package org.example.bizarreadventure.service;

import org.example.bizarreadventure.com.CommentStatus;
import org.example.bizarreadventure.entity.*;
import org.example.bizarreadventure.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private static final Logger logger = LoggerFactory.getLogger(CommentService.class);
    @Autowired
    private CommentRepository commentRepository;

    public Map<String, String> validateCommentData(String commentText) {
        Map<String, String> errors = new HashMap<>();
        if (commentText.isEmpty()) {
            errors.put("commentText", "Текст комментария пуст");
        }
        return errors;
    }

    public Comment addComment(User user_id, Anime anime, String commentText) {
        Comment comment = new Comment();
        comment.setUser(user_id);
        comment.setAnime(anime);
        comment.setComment(commentText);
        comment.setState(new PendingState());
        return commentRepository.save(comment);
    }
    public void approveComment(int commentId) {
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if (comment != null) {
            comment.setState(new ApprovedState());
            commentRepository.save(comment);
        }
    }

    public void rejectComment(int commentId) {
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if (comment != null) {
            comment.setState(new RejectedState());
            commentRepository.save(comment);
        }
    }

    public void deleteComment(int commentId) {
        commentRepository.deleteById(commentId);
    }

    public List<CommentDTO> getCommentsByAnimeId(int animeId) {
        List<Comment> comments = commentRepository.findByAnimeId(animeId);
        return comments.stream()
                .map(comment -> new CommentDTO(comment.getUser().getLogin(), comment.getComment(), comment.getStatus()))
                .collect(Collectors.toList());
    }

    public List<Comment> getCommentsByStatus(CommentStatus status) {
        return commentRepository.findByStatus(status);
    }

    public List<Comment> findPendingComments() {
        return commentRepository.findPendingComments();
    }

    public List<Comment> findApprovedComments() {
        return commentRepository.findApprovedComments();
    }

    public List<Comment> findRejectedComments() {
        return commentRepository.findRejectedComments();
    }

    public List<Comment> findCommentsByUser(User userId) {
        return commentRepository.findByAnime_UserId(userId);
    }

    public List<Comment> findPendingCommentsByUser(int userId) {
        return commentRepository.findPendingCommentsByUser(userId);
    }

    public List<Comment> findApprovedCommentsByUser(int userId) {
        return commentRepository.findApprovedCommentsByUser(userId);
    }

    public List<Comment> findRejectedCommentsByUser(int userId) {
        return commentRepository.findRejectedCommentsByUser(userId);
    }
}
