package com.rep.api.friend;

import com.rep.api.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "friends")
public class Friend {

    @EmbeddedId
    private FriendKey id;

    @ManyToOne
    @MapsId("creatorId")
    @JoinColumn(name = "creator_id", insertable = false, updatable = false)
    private User creator;

    @ManyToOne
    @MapsId("receiverId")
    @JoinColumn(name = "receiver_id", insertable = false, updatable = false)
    private User receiver;

    private boolean isActive;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime date;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
class FriendKey implements Serializable {

    @Column(name = "creator_id")
    private Long creatorId;

    @Column(name = "receiver_id")
    private Long receiverId;
}
