package com.rep.api.emoji;

import com.rep.api.season.Season;
import com.rep.api.season.SeasonRepository;
import com.rep.api.user.User;
import com.rep.api.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EmojiServiceImpl implements EmojiService {

    private final EmojiRepository emojiRepository;

    private final UserRepository userRepository;

    private final SeasonRepository seasonRepository;

    @Override
    public List<Emoji> findAll() {
        return emojiRepository.findAll();
    }

    @Override
    public Optional<Emoji> findById(Long id) {
        return emojiRepository.findById(id);
    }

    @Override
    public Set<Emoji> findUnlockedEmojiByUserId(Long userId) {
        return emojiRepository.findUnlockedEmojiByUserId(userId);
    }

    @Override
    public Set<Optional<Emoji>> findByPhaseAndSeasonId(int phase, Long seasonId) {
        return emojiRepository.findByPhaseAndSeasonId(phase, seasonId);
    }

    @Override
    public List<Emoji> findRandomEmojisByPhaseAndSeasonId(int phase, Long seasonId, int limit) {
        return emojiRepository.findRandomEmojisByPhaseAndSeasonId(phase, seasonId, limit);
    }

    @Override
    public void save(Emoji emoji) {
        boolean emojiExists = emojiRepository.findByEmojiCode(emoji.getEmojiCode()).isPresent();

        if (!emojiExists) {
            Season season = seasonRepository.findById(emoji.getSeasonId()).orElse(null);

            emoji.setSeason(season);

            emojiRepository.save(emoji);
        }
    }

    @Override
    public void addUserToEmoji(Long userId, String emojiCode) {
        User user = userRepository.findById(userId).orElse(null);
        Emoji emoji = emojiRepository.findByEmojiCode(emojiCode).orElse(null);

        if (user != null && emoji != null) {
            emoji.getUsers().add(user);
            emojiRepository.save(emoji);
        }
    }
}