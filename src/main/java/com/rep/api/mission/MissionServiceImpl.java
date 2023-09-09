package com.rep.api.mission;

import com.rep.api.emoji.Emoji;
import com.rep.api.emoji.EmojiRepository;
import com.rep.api.user.User;
import com.rep.api.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionServiceImpl implements MissionService {

    private final UserRepository userRepository;

    private final MissionRepository missionRepository;

    private final EmojiRepository emojiRepository;

    @Override
    public void save(Mission mission) {
        User creator = userRepository.findById(mission.getCreatorId()).orElse(null);
        Emoji emoji = emojiRepository.findById(mission.getEmojiId()).orElse(null);
        Emoji icon = emojiRepository.findById(mission.getIconId()).orElse(null);

        mission.setCreator(creator);
        mission.setEmoji(emoji);
        mission.setIcon(icon);

        missionRepository.save(mission);
    }

    @Override
    public void addUser(Long missionId, Long userId) {
        Mission mission = missionRepository.findById(missionId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);

        if (mission != null && user != null) {
            mission.addUser(user);
            missionRepository.save(mission);
        }
    }
}
