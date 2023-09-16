package com.rep.api.interaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

public interface InteractionRepository extends JpaRepository<Interaction, Long> {

    @Query(value = "SELECT i FROM Interaction i WHERE (i.user1Id IN (:user1Id, :user2Id) AND i.user2Id IN (:user1Id, :user2Id) AND i.date = :date)")
    Optional<Interaction> findByParams(@Param("user1Id") Long user1Id, @Param("user2Id") Long user2Id, @Param("date") LocalDateTime date);

    @Query(value = "SELECT i FROM Interaction i WHERE (:userId IN (i.user1Id, i.user2Id))")
    Set<Optional<Interaction>> findByOneUserId(@Param("userId") Long userId);

    @Query(value = "SELECT i FROM Interaction i WHERE i.isVisible")
    Set<Optional<Interaction>> findAllVisible();
}
