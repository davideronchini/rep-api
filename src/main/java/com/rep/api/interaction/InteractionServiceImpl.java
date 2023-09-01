package com.rep.api.interaction;

import com.rep.api.emoji.Emoji;
import com.rep.api.emoji.EmojiRepository;
import com.rep.api.season.Season;
import com.rep.api.season.SeasonRepository;
import com.rep.api.user.User;
import com.rep.api.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InteractionServiceImpl implements InteractionService {
    private final UserRepository userRepository;
    private final InteractionRepository interactionRepository;
    private final EmojiRepository emojiRepository;
    private final SeasonRepository seasonRepository;

    @Override
    public void createInteraction(InteractionDTO interactionDTO) {
        boolean interactionExists = interactionRepository.findByParams(
                interactionDTO.getUser1Id(),
                interactionDTO.getUser2Id(),
                interactionDTO.getDate()
        ).isPresent();

        if (!interactionExists) {
            User user1 = userRepository.findById(interactionDTO.getUser1Id()).orElse(null);
            User user2 = userRepository.findById(interactionDTO.getUser2Id()).orElse(null);
            Emoji emoji = emojiRepository.findById(interactionDTO.getEmojiId()).orElse(null);
            Season season = seasonRepository.findById(interactionDTO.getSeasonId()).orElse(null);

            Interaction interaction = new Interaction();
            interaction.setUser1(user1);
            interaction.setUser2(user2);
            interaction.setEmoji(emoji);
            interaction.setSeason(season);
            interaction.setVisible(interactionDTO.isVisible());
            interaction.setSpecial(interactionDTO.isSpecial());
            interaction.setDate(interactionDTO.getDate());

            interactionRepository.save(interaction);
        }
    }
}
