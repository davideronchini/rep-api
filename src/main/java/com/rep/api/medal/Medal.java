package com.rep.api.medal;

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
@Table(name = "medals")
public class Medal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(name = "creator_id")
    private Long creatorId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "medal_type_id")
    private Long medalTypeId;

    @ManyToOne
    @JoinColumn(name = "creator_id", insertable = false, updatable = false)
    private User creator;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "medal_type_id", insertable = false, updatable = false)
    private MedalType medalType;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime date;
}
