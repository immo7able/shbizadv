package org.example.bizarreadventure.repository;

import org.example.bizarreadventure.entity.Anime;
import org.example.bizarreadventure.entity.User;
import org.example.bizarreadventure.entity.UserList;
import org.hibernate.type.ListType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserListRepository extends JpaRepository<UserList, Integer> {
    @Query("SELECT ul FROM UserList ul WHERE ul.user_id = :user AND ul.anime = :anime")
    UserList findByUserAndAnime(@Param("user") User user, @Param("anime") Anime anime);

    @Query("SELECT ul FROM UserList ul WHERE ul.user_id = :user AND ul.listType = :listType")
    List<UserList> findByUserAndListType(@Param("user") User user, @Param("listType") String listType);
}

