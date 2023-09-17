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
        nodes.add(Map.of("id", 0, "emoji", "", "locked", false));
        nodes.add(Map.of("id", 1, "emoji", "\uD83D\uDE04", "locked", true));
        nodes.add(Map.of("id", 2, "emoji", "\uD83D\uDE06", "locked", false));
        nodes.add(Map.of("id", 3, "emoji", "\uD83D\uDE02", "locked", false));
        nodes.add(Map.of("id", 4, "emoji", "\uD83D\uDE0D", "locked", false));
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
}
