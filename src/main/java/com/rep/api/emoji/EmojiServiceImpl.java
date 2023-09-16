package com.rep.api.emoji;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmojiServiceImpl implements EmojiService{

    private final EmojiRepository emojiRepository;

    @Override
    public List<Emoji> findAll() {
        return emojiRepository.findAll();
    }

    @Override
    public void save(Emoji emoji) {
        emojiRepository.save(emoji);
    }
}
