package com.rep.api.emoji_map;

import com.rep.api.season.Season;
import com.rep.api.season.SeasonRepository;
import com.rep.api.user.User;
import com.rep.api.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmojiMapServiceImpl implements EmojiMapService {

    private final EmojiMapRepository emojiMapRepository;

    private final UserRepository userRepository;

    private final SeasonRepository seasonRepository;

    @Override
    public List<EmojiMap> findAll() {
        return emojiMapRepository.findAll();
    }

    @Override
    public Optional<EmojiMap> findByUserIdAndSeasonId(Long userId, Long seasonId) {
        return emojiMapRepository.findByUserIdAndSeasonId(userId, seasonId);
    }

    @Override
    public EmojiMap save(EmojiMap emojiMap) {
        EmojiMap emojiMapsFound = emojiMapRepository.findByUserIdAndSeasonId(emojiMap.getUserId(), emojiMap.getSeasonId()).orElse(null);

        if (emojiMapsFound == null) {
            User user = userRepository.findById(emojiMap.getUserId()).orElse(null);
            Season season = seasonRepository.findById(emojiMap.getSeasonId()).orElse(null);

            emojiMap.setUser(user);
            emojiMap.setSeason(season);

            return emojiMapRepository.save(emojiMap);
        }

        return emojiMapsFound;
    }
}