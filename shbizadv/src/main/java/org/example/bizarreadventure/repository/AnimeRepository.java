package org.example.bizarreadventure.repository;
import org.example.bizarreadventure.entity.Anime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimeRepository extends JpaRepository<Anime, Integer> {
    boolean existsByName(String name);
    @Query(value = "SELECT * FROM anime a WHERE a.name ILIKE %:name%", nativeQuery = true)
    List<Anime> findAllByName(String name);
}
