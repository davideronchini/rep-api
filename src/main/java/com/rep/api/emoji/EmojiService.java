package com.rep.api.emoji;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface EmojiService {

    List<Emoji> findAll();

    Optional<Emoji> findById(Long id);

    Set<Emoji> findUnlockedEmojiByUserId(Long userId);

    Set<Optional<Emoji>> findByPhaseAndSeasonId(int phase, Long seasonId);

    List<Emoji> findRandomEmojisByPhaseAndSeasonId(int phase, Long seasonId, int limit);

    void save(Emoji emoji);

    void addUserToEmoji(Long userId, String emojiCode);
}
