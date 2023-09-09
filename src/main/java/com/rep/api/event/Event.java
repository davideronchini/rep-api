package com.rep.api.event;

import com.rep.api.emoji.Emoji;
import com.rep.api.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    private byte[] image;

    @Column(name = "creator_id")
    private Long creatorId;

    @Column(name = "emoji_id")
    private Long emojiId;

    @ManyToOne
    @JoinColumn(name = "creator_id", insertable = false, updatable = false)
    private User creator;

    @ManyToOne
    @JoinColumn(name = "emoji_id", insertable = false, updatable = false)
    private Emoji emoji;

    @ManyToMany()
    @JoinTable(
            name = "users_events",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime date;

    public void addUser(User user) {
        users.add(user);

        user.getEvents().add(this);
    }
}
