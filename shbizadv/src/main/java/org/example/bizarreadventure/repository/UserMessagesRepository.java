package org.example.bizarreadventure.repository;

import org.example.bizarreadventure.entity.Anime;
import org.example.bizarreadventure.entity.SubscribeList;
import org.example.bizarreadventure.entity.User;
import org.example.bizarreadventure.entity.UserMessages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface UserMessagesRepository extends JpaRepository<UserMessages, Integer> {
    @Query("SELECT um FROM UserMessages um WHERE um.user = :user")
    ArrayList<UserMessages> findByUser(@Param("user") User user);
}
