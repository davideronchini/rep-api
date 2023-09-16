package com.rep.api.emoji;

import java.util.List;

public interface EmojiService{

    List<Emoji> findAll();

    void save(Emoji emoji);
}
