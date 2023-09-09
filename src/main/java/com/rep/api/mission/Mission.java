package com.rep.api.mission;

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
@Table(name = "missions")
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String address;

    @Column(name = "creator_id")
    private Long creatorId;

    @Column(name = "emoji_id")
    private Long emojiId;

    @Column(name = "icon_id")
    private Long iconId;

    @ManyToOne
    @JoinColumn(name = "creator_id", insertable = false, updatable = false)
    private User creator;

    @ManyToOne
    @JoinColumn(name = "emoji_id", insertable = false, updatable = false)
    private Emoji emoji;

    @ManyToOne
    @JoinColumn(name = "icon_id", insertable = false, updatable = false)
    private Emoji icon;

    @ManyToMany()
    @JoinTable(
            name = "users_missions",
            joinColumns = @JoinColumn(name = "mission_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime date;

    public void addUser(User user) {
        users.add(user);

        user.getMissions().add(this);
    }
}
