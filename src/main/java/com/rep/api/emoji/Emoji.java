package com.rep.api.emoji;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rep.api.season.Season;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties({"season"})
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

    private String emojiSymbol;

    private int phase;

    private boolean isLocked;

    @Column(name = "season_id")
    private Long seasonId;

    @ManyToOne
    @JoinColumn(name = "season_id", insertable = false, updatable = false)
    private Season season;
}
