package com.rep.api.emoji;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface EmojiService{

    List<Emoji> findAll();

    Set<Optional<Emoji>> findByPhaseAndSeasonId(int phase, Long seasonId);

    void save(Emoji emoji);
}
