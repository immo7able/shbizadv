package org.example.bizarreadventure.entity;

import jakarta.persistence.*;
import org.hibernate.type.ListType;

@Entity
@Table(name = "userlist")
public class UserList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userlist_id")
    private int userlistId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user_id;

    @ManyToOne
    @JoinColumn(name = "anime_id")
    private Anime anime;
    @Column(name = "listtype")
    private String listType;

    public int getUserlistId() {
        return userlistId;
    }

    public void setUserlistId(int userlistId) {
        this.userlistId = userlistId;
    }

    public User getUser() {
        return user_id;
    }

    public void setUser(User user_id) {
        this.user_id = user_id;
    }

    public Anime getAnime() {
        return  anime;
    }

    public void setAnime(Anime anime) {
        this.anime = anime;
    }


    public String getListType() {
        return listType;
    }

    public void setListType(String listType) {
        this.listType = listType;
    }
}
