package com.rep.api.emoji_map;

import java.util.List;
import java.util.Optional;

public interface EmojiMapService {

    List<EmojiMap> findAll();

    Optional<EmojiMap> findByUserIdAndSeasonId(Long userId, Long seasonId);

    EmojiMap save(EmojiMap emojiMap);
}