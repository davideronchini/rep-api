package com.rep.api.emoji;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rep.api.season.Season;
import com.rep.api.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties({"season", "users"})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "emojis")
public class Emoji {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String emojiCode;

    private int phase;

    @Column(name = "season_id")
    private Long seasonId;

    @ManyToOne
    @JoinColumn(name = "season_id", insertable = false, updatable = false)
    private Season season;

    @ManyToMany()
    @JoinTable(name = "emojis_unlocked",
            joinColumns = { @JoinColumn(name = "emoji_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") })
    private Set<User> users = new HashSet<>();
}