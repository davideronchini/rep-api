package com.rep.api.emoji;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmojiServiceImpl implements EmojiService{

    private final EmojiRepository emojiRepository;
}
