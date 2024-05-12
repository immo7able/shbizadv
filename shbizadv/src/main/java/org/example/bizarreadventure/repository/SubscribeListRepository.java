package org.example.bizarreadventure.repository;

import org.example.bizarreadventure.entity.Anime;
import org.example.bizarreadventure.entity.SubscribeList;
import org.example.bizarreadventure.entity.User;
import org.example.bizarreadventure.entity.UserList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface SubscribeListRepository extends JpaRepository<SubscribeList, Integer> {
    @Query("SELECT sl FROM SubscribeList sl WHERE sl.user = :user AND sl.anime = :anime")
    SubscribeList findByUserAndAnime(@Param("user") User user, @Param("anime") Anime anime);
    @Query("SELECT sl FROM SubscribeList sl WHERE sl.anime = :anime")
    ArrayList<SubscribeList> findByAnime(@Param("anime") Anime anime);
}
