package com.rep.api.emoji;

import com.rep.api.season.Season;
import com.rep.api.season.SeasonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EmojiServiceImpl implements EmojiService{

    private final EmojiRepository emojiRepository;

    private final SeasonRepository seasonRepository;

    @Override
    public List<Emoji> findAll() {
        return emojiRepository.findAll();
    }

    @Override
    public Set<Optional<Emoji>> findByPhaseAndSeasonId(int phase, Long seasonId) {
        return emojiRepository.findByPhaseAndSeasonId(phase, seasonId);
    }

    @Override
    public void save(Emoji emoji) {
        boolean emojiExists = emojiRepository.findByEmojiSymbol(emoji.getEmojiSymbol()).isPresent();

        if (!emojiExists){
            Season season = seasonRepository.findById(emoji.getSeasonId()).orElse(null);

            emoji.setSeason(season);

            emojiRepository.save(emoji);
        }
    }
}
