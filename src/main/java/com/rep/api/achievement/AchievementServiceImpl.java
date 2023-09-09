package com.rep.api.achievement;

import com.rep.api.emoji.Emoji;
import com.rep.api.emoji.EmojiRepository;
import com.rep.api.user.User;
import com.rep.api.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AchievementServiceImpl implements AchievementService {

    private final UserRepository userRepository;

    private final AchievementRepository achievementRepository;

    private final EmojiRepository emojiRepository;

    @Override
    public void save(Achievement achievement) {
        Emoji emoji = emojiRepository.findById(achievement.getEmojiId()).orElse(null);

        achievement.setEmoji(emoji);

        achievementRepository.save(achievement);
    }

    @Override
    public void addUser(Long achievementId, Long userId) {
        Achievement achievement = achievementRepository.findById(achievementId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);

        if (achievement != null && user != null) {
            achievement.addUser(user);
            achievementRepository.save(achievement);
        }
    }
}
