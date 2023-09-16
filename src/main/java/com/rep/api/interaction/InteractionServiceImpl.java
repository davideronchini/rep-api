package com.rep.api.interaction;

import com.rep.api.emoji.Emoji;
import com.rep.api.emoji.EmojiRepository;
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
public class InteractionServiceImpl implements InteractionService {
    private final UserRepository userRepository;
    private final InteractionRepository interactionRepository;
    private final EmojiRepository emojiRepository;
    private final SeasonRepository seasonRepository;

    @Override
    public List<Interaction> findAll() {
        return interactionRepository.findAll();
    }

    @Override
    public Set<Optional<Interaction>> findAllVisible() {
        return interactionRepository.findAllVisible();
    }

    @Override
    public void save(Interaction interaction) {
        boolean interactionExists = interactionRepository.findByParams(
                interaction.getUser1Id(),
                interaction.getUser2Id(),
                interaction.getDate()
        ).isPresent();

        if (!interactionExists) {
            User user1 = userRepository.findById(interaction.getUser1Id()).orElse(null);
            User user2 = userRepository.findById(interaction.getUser2Id()).orElse(null);
            Emoji emoji = emojiRepository.findById(interaction.getEmojiId()).orElse(null);
            Season season = seasonRepository.findById(interaction.getSeasonId()).orElse(null);

            interaction.setUser1(user1);
            interaction.setUser2(user2);
            interaction.setEmoji(emoji);
            interaction.setSeason(season);

            interactionRepository.save(interaction);
        }
    }
}
