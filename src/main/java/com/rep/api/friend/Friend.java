package com.rep.api.friend;

import com.rep.api.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "friends")
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //TODO: use an embeddable class like in the post tag one and reuse this code to fix the interaction code
    @Column(name = "creator_id")
    private Long creatorId;

    @Column(name = "receiver_id")
    private Long receiverId;

    @ManyToOne
    @JoinColumn(name = "creator_id", insertable = false, updatable = false)
    private User creator;

    @ManyToOne
    @JoinColumn(name = "receiver_id", insertable = false, updatable = false)
    private User receiver;

    private boolean isActive;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime date;
}
