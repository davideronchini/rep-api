package com.rep.api.emoji_map;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmojiMapRepository extends JpaRepository<EmojiMap, Long> {

    Optional<EmojiMap> findByUserIdAndSeasonId(Long userId, Long seasonId);
}