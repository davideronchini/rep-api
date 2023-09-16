package com.rep.api.interaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rep.api.emoji.Emoji;
import com.rep.api.season.Season;
import com.rep.api.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@JsonIgnoreProperties({"user1", "user2", "emoji", "season"})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "interactions")
public class Interaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user1_id")
    private Long user1Id;

    @Column(name = "user2_id")
    private Long user2Id;

    @Column(name = "emoji_id")
    private Long emojiId;

    @Column(name = "season_id")
    private Long seasonId;

    @ManyToOne
    @JoinColumn(name = "user1_id", insertable = false, updatable = false)
    private User user1;

    @ManyToOne
    @JoinColumn(name = "user2_id", insertable = false, updatable = false)
    private User user2;

    @ManyToOne
    @JoinColumn(name = "emoji_id", insertable = false, updatable = false)
    private Emoji emoji;

    @ManyToOne
    @JoinColumn(name = "season_id", insertable = false, updatable = false)
    private Season season;

    private boolean isVisible = true;

    private boolean isSpecial = false;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime date;
}