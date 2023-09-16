package com.rep.api.emoji;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rep-api/v1/emojis")
@RequiredArgsConstructor
public class EmojiController {

    private final EmojiService emojiService;

    @GetMapping("/")
    public ResponseEntity<List<Emoji>> findAll() {
        return ResponseEntity.ok(emojiService.findAll());
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody Emoji emoji) {
        emojiService.save(emoji);

        return ResponseEntity.ok("Emoji created successfully");
    }
}
