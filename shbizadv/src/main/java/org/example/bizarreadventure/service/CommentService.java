package org.example.bizarreadventure.service;

import org.example.bizarreadventure.entity.Anime;
import org.example.bizarreadventure.entity.Comment;
import org.example.bizarreadventure.entity.CommentDTO;
import org.example.bizarreadventure.entity.User;
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
        return commentRepository.save(comment);
    }
    public List<CommentDTO> getCommentsByAnimeId(int animeId) {
        List<Comment> comments = commentRepository.findByAnimeId(animeId);
        List<CommentDTO> commentDTOs = comments.stream()
                .map(comment -> new CommentDTO(comment.getUser().getLogin(), comment.getComment()))
                .collect(Collectors.toList());
        logger.info("Loaded comments: {}", commentDTOs);
        return commentDTOs;
    }

}
