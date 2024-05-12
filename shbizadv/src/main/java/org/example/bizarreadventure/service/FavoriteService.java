package org.example.bizarreadventure.service;

import org.example.bizarreadventure.entity.Anime;
import org.example.bizarreadventure.entity.User;
import org.example.bizarreadventure.entity.UserList;
import org.example.bizarreadventure.repository.UserListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.hibernate.type.ListType;

import java.util.List;

@Service
public class FavoriteService {
    @Autowired
    private  UserListRepository userListRepository;


    public void addToFavorites(User user, Anime anime) {
        UserList existingFavorite = userListRepository.findByUserAndAnime(user, anime);

        if (existingFavorite == null) {
            UserList newUserList = new UserList();
            newUserList.setUser(user);
            newUserList.setAnime(anime);
            newUserList.setListType("FAVORITES");

            userListRepository.save(newUserList);
        }
    }

    public List<UserList> findFavoritesByUserId(User user_id) {
        return userListRepository.findByUserAndListType(user_id, "FAVORITES");
    }
}
