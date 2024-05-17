package org.example.bizarreadventure.com;

import org.example.bizarreadventure.entity.Comment;

public interface CommentState {
    void handle(Comment comment);
}

