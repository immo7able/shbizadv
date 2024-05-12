package org.example.bizarreadventure.entity;

import jakarta.persistence.*;
@Entity
@Table(name = "usermessage")
public class UserMessages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int subscribe_id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column
    private String message;

    public int getSubscribe_id() {
        return subscribe_id;
    }

    public void setSubscribe_id(int subscribe_id) {
        this.subscribe_id = subscribe_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
