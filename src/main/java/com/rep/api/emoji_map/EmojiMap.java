package com.rep.api.emoji_map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rep.api.season.Season;
import com.rep.api.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@JsonIgnoreProperties({"season", "user"})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "emoji_maps")
public class EmojiMap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "band1EmojiIds")
    private ArrayList<Long> band1EmojiIds = new ArrayList<>(8);

    @JoinColumn(name = "foodEmojiIds")
    private ArrayList<Long> foodEmojiIds = new ArrayList<>(4);

    @JoinColumn(name = "sportEmojiIds")
    private ArrayList<Long> sportEmojiIds = new ArrayList<>(4);

    @JoinColumn(name = "band4EmojiIds")
    private ArrayList<Long> band4EmojiIds = new ArrayList<>(4);

    @JoinColumn(name = "band5EmojiIds")
    private ArrayList<Long> band5EmojiIds = new ArrayList<>(8);

    @JoinColumn(name = "band6EmojiIds")
    private ArrayList<Long> band6EmojiIds = new ArrayList<>(4);

    @JoinColumn(name = "heartsEmojiIds")
    private ArrayList<Long> heartsEmojiIds = new ArrayList<>(4);

    @Column(name = "season_id")
    private Long seasonId;

    @ManyToOne
    @JoinColumn(name = "season_id", insertable = false, updatable = false)
    private Season season;

    @Column(name = "user_id")
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;
}