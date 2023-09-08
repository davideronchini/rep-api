package com.rep.api.interaction;

import com.rep.api.emoji.Emoji;
import com.rep.api.emoji.EmojiRepository;
import com.rep.api.season.Season;
import com.rep.api.season.SeasonRepository;
import com.rep.api.user.User;
import com.rep.api.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class InteractionRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InteractionRepository interactionRepository;

    @Autowired
    private EmojiRepository emojiRepository;

    @Autowired
    private SeasonRepository seasonRepository;

    @BeforeEach
    void setUp() {
        // Register the users
        User u1 = new User();
        u1.setId(1L);
        u1.setFirstName("User 1");
        u1.setLastName("Test");
        u1.setEmail("user1@gmail.com");
        userRepository.save(u1);

        User u2 = new User();
        u2.setId(2L);
        u2.setFirstName("User 1");
        u2.setLastName("Test");
        u2.setEmail("user2@gmail.com");
        userRepository.save(u2);

        // Get the users
        User user1 = userRepository.findByEmail("user1@gmail.com").orElse(null);
        User user2 = userRepository.findByEmail("user2@gmail.com").orElse(null);

        // Save the emoji
        emojiRepository.save(new Emoji(1L, "", false));

        // Get the emoji
        Emoji emoji = emojiRepository.findById(1L).orElse(null);

        // Save the season
        seasonRepository.save(new Season(1L, 1, LocalDateTime.parse("2023-07-10T14:30:00"), LocalDateTime.parse("2023-08-10T14:30:00")));

        // Get the season
        Season season = seasonRepository.findById(1L).orElse(null);

        // Save the interaction
        interactionRepository.save(new Interaction(1L, 1L, 2L, 1L, 1L, user1, user2, emoji, season, true, false, LocalDateTime.parse("2023-07-28T14:30:00")));
    }

    @Test
    void test() {
        findByParams();
        findByOneUserId();
    }


    void findByParams() {
        Optional<Interaction> foundInteraction1 = interactionRepository.findByParams(1L, 2L, LocalDateTime.parse("2023-07-28T14:30:00"));
        Optional<Interaction> foundInteraction2 = interactionRepository.findByParams(2L, 1L, LocalDateTime.parse("2023-07-28T14:30:00"));

        assertThat(foundInteraction1).isNotNull().isNotEmpty();
        assertThat(foundInteraction2).isNotNull().isNotEmpty();
    }

    void findByOneUserId() {
        Set<Optional<Interaction>> foundInteractions1 = interactionRepository.findByOneUserId(1L);
        Set<Optional<Interaction>> foundInteractions2 = interactionRepository.findByOneUserId(2L);

        assertThat(foundInteractions1).isNotNull().isNotEmpty();
        assertThat(foundInteractions2).isNotNull().isNotEmpty();
    }
}
