package org.example.bizarreadventure.entity;

import org.example.bizarreadventure.com.CommentStatus;

public class CommentDTO {
    private String login;
    private String commentText;
    private CommentStatus status;

    public CommentDTO(String login, String commentText, CommentStatus status) {
        this.login = login;
        this.commentText = commentText;
        this.status = status;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public CommentStatus getStatus() {
        return status;
    }

    public void setStatus(CommentStatus status) {
        this.status = status;
    }
}


