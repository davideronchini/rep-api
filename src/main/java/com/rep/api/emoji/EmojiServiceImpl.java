package com.rep.api.emoji;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmojiServiceImpl {

    private final EmojiRepository emojiRepository;
}
