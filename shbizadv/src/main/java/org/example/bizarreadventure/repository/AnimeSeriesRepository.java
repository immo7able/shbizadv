package org.example.bizarreadventure.repository;

import org.example.bizarreadventure.entity.AnimeSeries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimeSeriesRepository extends JpaRepository<AnimeSeries, Integer> {
    boolean existsByNumberAndAnimeid(int number, int id);
    List<AnimeSeries> findAllByAnimeidOrderByNumber(int anime_id);
}
