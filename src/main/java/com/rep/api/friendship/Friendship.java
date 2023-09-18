package com.rep.api.friendship;

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
@Table(name = "friendships")
public class Friendship {

    @EmbeddedId
    private FriendshipKey id;

    @ManyToOne
    @MapsId("creatorId")
    @JoinColumn(name = "creator_id", insertable = false, updatable = false)
    private User creator;

    @ManyToOne
    @MapsId("receiverId")
    @JoinColumn(name = "receiver_id", insertable = false, updatable = false)
    private User receiver;

    private boolean isAccepted = false;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime date;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
class FriendshipKey implements Serializable {

    @Column(name = "creator_id")
    private Long creatorId;

    @Column(name = "receiver_id")
    private Long receiverId;
}
