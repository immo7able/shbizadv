package org.example.bizarreadventure.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.example.bizarreadventure.com.CommentState;
import org.example.bizarreadventure.com.CommentStatus;

@Entity
@Table(name = "animecomment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int animecomment_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "anime_id")
    private Anime anime;

    @Enumerated(EnumType.STRING)
    private CommentStatus status;

    @Transient
    private CommentState state;
    public Comment(){
        this.state= new PendingState();
        state.handle(this);
    }

    public void setState(CommentState state) {
        this.state = state;
        this.state.handle(this);
    }

    public CommentStatus getStatus() {
        return status;
    }

    public void setStatus(CommentStatus status) {
        this.status = status;
    }
    public int getAnimecomment_id() {
        return animecomment_id;
    }

    public void setAnimecomment_id(int animecomment_id) {
        this.animecomment_id = animecomment_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Anime getAnime() {
        return anime;
    }

    public void setAnime(Anime anime) {
        this.anime = anime;
    }
}
