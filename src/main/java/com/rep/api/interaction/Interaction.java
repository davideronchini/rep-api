package com.rep.api.interaction;

import com.rep.api.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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


    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime date;

    private boolean isVisible = true;

    private boolean isSpecial = false;

    @ManyToOne
    @JoinColumn(name = "user1")
    private User user1;

    @ManyToOne
    @JoinColumn(name = "user2")
    private User user2;

    private Long emojiId;

    private Long seasonId;
}