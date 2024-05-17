package org.example.bizarreadventure.entity;
import org.example.bizarreadventure.com.CommentState;
import org.example.bizarreadventure.com.CommentStatus;

public class RejectedState implements CommentState {
    @Override
    public void handle(Comment comment) {
        comment.setStatus(CommentStatus.REJECTED);
    }
}
