package org.example.bizarreadventure.repository;
import org.example.bizarreadventure.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Integer>{

}
