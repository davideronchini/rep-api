package com.rep.api.emoji;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface EmojiRepository extends JpaRepository<Emoji, Long> {

    Optional<Emoji> findByEmojiCode(String emojiCode);

    @Query("SELECT e FROM Emoji e JOIN e.users u WHERE u.id = :userId")
    Set<Emoji> findUnlockedEmojiByUserId(@Param("userId") Long userId);

    Set<Optional<Emoji>> findByPhaseAndSeasonId(int phase, Long seasonId);

    @Query(value = "SELECT e FROM Emoji e WHERE e.phase = :phase AND e.seasonId = :seasonId ORDER BY RAND() LIMIT :limit")
    List<Emoji> findRandomEmojisByPhaseAndSeasonId(@Param("phase") int phase, @Param("seasonId") Long seasonId, @Param("limit") int limit);
}