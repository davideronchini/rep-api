package com.rep.api.event;

import com.rep.api.emoji.Emoji;
import com.rep.api.emoji.EmojiRepository;
import com.rep.api.user.User;
import com.rep.api.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final UserRepository userRepository;

    private final EventRepository eventRepository;

    private final EmojiRepository emojiRepository;

    @Override
    public void save(Event event) {
        User creator = userRepository.findById(event.getCreatorId()).orElse(null);
        Emoji emoji = emojiRepository.findById(event.getEmojiId()).orElse(null);

        event.setCreator(creator);
        event.setEmoji(emoji);

        eventRepository.save(event);

    }

    @Override
    public void addUser(long eventId, Long userId) {
        Event event = eventRepository.findById(eventId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);

        if (event != null && user != null) {
            event.addUser(user);
            eventRepository.save(event);
        }
    }
}
