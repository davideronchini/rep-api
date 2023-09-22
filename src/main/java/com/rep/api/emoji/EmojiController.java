package com.rep.api.emoji;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/rep-api/v1/emojis")
@RequiredArgsConstructor
public class EmojiController {

    private final EmojiService emojiService;

    @GetMapping("/all")
    public ResponseEntity<List<Emoji>> findAll() {
        return ResponseEntity.ok(emojiService.findAll());
    }

    @GetMapping("/findByPhaseAndSeason")
    public ResponseEntity<Set<Optional<Emoji>>> findByPhaseAndSeasonId(@RequestParam int phase, @RequestParam Long seasonId) {
        return ResponseEntity.ok(emojiService.findByPhaseAndSeasonId(phase, seasonId));
    }

    @GetMapping("/map")
    public ResponseEntity<String> getMap() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> data = new HashMap<>();
        List<Map<String, Object>> nodes = new ArrayList<>();
        nodes.add(Map.of("id", 0, "name", "unlocks available", "emojiCode", ""));
        nodes.add(Map.of("id", 1, "name", "smiling face with heart-eyes", "emojiCode", "\uD83D\uDE0D"));
        nodes.add(Map.of("id", 2, "name", "star-struck", "emojiCode", "\uD83E\uDD29"));
        nodes.add(Map.of("id", 3, "name", "face with tears of joy", "emojiCode", "\uD83D\uDE02"));
        nodes.add(Map.of("id", 4, "name", "beaming face with smiling eyes", "emojiCode", "\uD83D\uDE01"));
        data.put("nodes", nodes);

        List<Map<String, Object>> edges = new ArrayList<>();
        edges.add(Map.of("from", 0, "to", 1));
        edges.add(Map.of("from", 0, "to", 2));
        edges.add(Map.of("from", 0, "to", 3));
        edges.add(Map.of("from", 0, "to", 4));
        data.put("edges", edges);

        return ResponseEntity.ok(objectMapper.writeValueAsString(data));
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody Emoji emoji) {
        emojiService.save(emoji);

        return ResponseEntity.ok("Emoji created successfully");
    }

    @PostMapping("/unlock-emoji")
    public ResponseEntity<String> addUserToEmoji(@RequestParam Long userId, @RequestParam String emojiCode) {
        emojiService.addUserToEmoji(userId, emojiCode);

        return ResponseEntity.ok("The user has successfully unlocked the emoji");
    }

    @GetMapping("/unlocked-emoji")
    public ResponseEntity<Set<Emoji>> findUnlockedEmojiByUserId(@RequestParam Long userId) {
        return ResponseEntity.ok(emojiService.findUnlockedEmojiByUserId(userId));
    }
}