package com.rep.api.emoji_map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rep.api.emoji.Emoji;
import com.rep.api.emoji.EmojiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/rep-api/v1/emoji_maps")
@RequiredArgsConstructor
public class EmojiMapController {

    private final EmojiMapService emojiMapService;

    private final EmojiService emojiService;

    @GetMapping("/all")
    public ResponseEntity<List<EmojiMap>> findAll() {
        return ResponseEntity.ok(emojiMapService.findAll());
    }

    @GetMapping("/findByUserAndSeason")
    public ResponseEntity<Optional<EmojiMap>> findByUserAndSeason(@RequestParam Long userId, @RequestParam Long seasonId) {
        return ResponseEntity.ok(emojiMapService.findByUserIdAndSeasonId(userId, seasonId));
    }

    @GetMapping("/map")
    public ResponseEntity<String> getMapByUserIdAndSeasonId(
            @RequestParam Long userId, @RequestParam Long seasonId) throws JsonProcessingException {
        EmojiMap emojiMap = emojiMapService.findByUserIdAndSeasonId(userId, seasonId)
                .orElseGet(() -> createRandomEmojiMap(userId, seasonId));

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> data = new HashMap<>();
        List<Map<String, Object>> nodes = new ArrayList<>();
        nodes.add(Map.of("id", 0, "name", "unlocks available", "emojiCode", ""));

        addNodes(nodes, emojiMap.getBand1EmojiIds(), 1, seasonId, 8);
        addNodes(nodes, emojiMap.getBand1EmojiIds(), 29, seasonId, 4);
        addNodes(nodes, emojiMap.getFoodEmojiIds(), 5, seasonId, 4);
        addNodes(nodes, emojiMap.getSportEmojiIds(), 9, seasonId, 4);
        addNodes(nodes, emojiMap.getBand4EmojiIds(), 13, seasonId, 4);
        addNodes(nodes, emojiMap.getBand5EmojiIds(), 17, seasonId, 8);
        addNodes(nodes, emojiMap.getBand5EmojiIds(), 21, seasonId, 4);
        addNodes(nodes, emojiMap.getBand6EmojiIds(), 25, seasonId, 4);
        addNodes(nodes, emojiMap.getHeartsEmojiIds(), 33, seasonId, 4);

        List<Map<String, Integer>> edges = new ArrayList<>();
        addEdges(edges, 0, 1, 2, 3, 4);
        addEdges(edges, 1, 5, 9);
        addEdges(edges, 2, 6, 10);
        addEdges(edges, 3, 7, 11);
        addEdges(edges, 4, 8, 12);
        addEdges(edges, 5, 13);
        addEdges(edges, 6, 14);
        addEdges(edges, 7, 15);
        addEdges(edges, 8, 16);
        addEdges(edges, 9, 13);
        addEdges(edges, 10, 14);
        addEdges(edges, 11, 15);
        addEdges(edges, 12, 16);
        addEdges(edges, 13, 17, 21);
        addEdges(edges, 14, 18, 22);
        addEdges(edges, 15, 19, 23);
        addEdges(edges, 16, 20, 24);
        addEdges(edges, 21, 25);
        addEdges(edges, 22, 26);
        addEdges(edges, 23, 27);
        addEdges(edges, 24, 28);
        addEdges(edges, 25, 29);
        addEdges(edges, 26, 30);
        addEdges(edges, 27, 31);
        addEdges(edges, 28, 32);
        addEdges(edges, 29, 33);
        addEdges(edges, 30, 34);
        addEdges(edges, 31, 35);
        addEdges(edges, 32, 36);

        data.put("nodes", nodes);
        data.put("edges", edges);

        return ResponseEntity.ok(objectMapper.writeValueAsString(data));
    }

    private void addNodes(List<Map<String, Object>> nodes, List<Long> emojiIds, int startId, Long seasonId, int limit) {
        List<Emoji> randomEmojis = emojiService.findRandomEmojisByPhaseAndSeasonId(1, seasonId, limit);

        for (int i = 0; i < limit; i++) {
            Emoji currentEmoji = emojiIds.size() > i ? emojiService.findById(emojiIds.get(i)).orElse(null) : randomEmojis.get(i);
            if (currentEmoji != null) {
                nodes.add(Map.of(
                        "id", startId + i,
                        "emojiId", currentEmoji.getId(),
                        "emojiCode", currentEmoji.getEmojiCode()
                ));
            }
        }
    }

    private void addEdges(List<Map<String, Integer>> edges, int from, int... to) {
        for (int target : to) {
            edges.add(Map.of("from", from, "to", target));
        }
    }

    private EmojiMap createRandomEmojiMap(Long userId, Long seasonId) {
        EmojiMap emojiMap = new EmojiMap();
        emojiMap.setUserId(userId);
        emojiMap.setSeasonId(seasonId);

        List<Emoji> randomBand1Emojis = emojiService.findRandomEmojisByPhaseAndSeasonId(1, seasonId, 8);
        List<Emoji> randomBand5Emojis = emojiService.findRandomEmojisByPhaseAndSeasonId(5, seasonId, 8);
        List<Emoji> randomFoodEmojis = emojiService.findRandomEmojisByPhaseAndSeasonId(2, seasonId, 4);
        List<Emoji> randomSportEmojis = emojiService.findRandomEmojisByPhaseAndSeasonId(3, seasonId, 4);
        List<Emoji> randomBand4Emojis = emojiService.findRandomEmojisByPhaseAndSeasonId(6, seasonId, 4);
        List<Emoji> randomBand6Emojis = emojiService.findRandomEmojisByPhaseAndSeasonId(4, seasonId, 4);
        List<Emoji> randomHeartsEmojis = emojiService.findRandomEmojisByPhaseAndSeasonId(7, seasonId, 4);

        ArrayList<Long> band1EmojiIds = new ArrayList<>();
        ArrayList<Long> band5EmojiIds = new ArrayList<>();
        ArrayList<Long> foodEmojiIds = new ArrayList<>();
        ArrayList<Long> sportEmojiIds = new ArrayList<>();
        ArrayList<Long> band4EmojiIds = new ArrayList<>();
        ArrayList<Long> band6EmojiIds = new ArrayList<>();
        ArrayList<Long> heartsEmojiIds = new ArrayList<>();

        // Add random emoji IDs to lists
        for (Emoji emoji : randomBand1Emojis) {
            band1EmojiIds.add(emoji.getId());
        }
        for (Emoji emoji : randomBand5Emojis) {
            band5EmojiIds.add(emoji.getId());
        }
        for (Emoji emoji : randomFoodEmojis) {
            foodEmojiIds.add(emoji.getId());
        }
        for (Emoji emoji : randomSportEmojis) {
            sportEmojiIds.add(emoji.getId());
        }
        for (Emoji emoji : randomBand4Emojis) {
            band4EmojiIds.add(emoji.getId());
        }
        for (Emoji emoji : randomBand6Emojis) {
            band6EmojiIds.add(emoji.getId());
        }
        for (Emoji emoji : randomHeartsEmojis) {
            heartsEmojiIds.add(emoji.getId());
        }

        // Sets ID lists in the EmojiMap object
        emojiMap.setBand1EmojiIds(band1EmojiIds);
        emojiMap.setBand5EmojiIds(band5EmojiIds);
        emojiMap.setFoodEmojiIds(foodEmojiIds);
        emojiMap.setSportEmojiIds(sportEmojiIds);
        emojiMap.setBand4EmojiIds(band4EmojiIds);
        emojiMap.setBand6EmojiIds(band6EmojiIds);
        emojiMap.setHeartsEmojiIds(heartsEmojiIds);

        // Save the EmojiMap to the database
        return emojiMapService.save(emojiMap);
    }
}