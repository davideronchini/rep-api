package com.rep.api.emoji;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface EmojiRepository extends JpaRepository<Emoji, Long> {

    Optional<Emoji> findByEmojiSymbol(String emojiSymbol);

    Set<Optional<Emoji>> findByPhaseAndSeasonId(int phase, Long seasonId);
}
