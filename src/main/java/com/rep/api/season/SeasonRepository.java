package com.rep.api.season;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SeasonRepository extends JpaRepository<Season, Long> {
    @Query(value = "SELECT s FROM Season s ORDER BY s.endDate DESC")
    Optional<Season> findLastSeason();
}
