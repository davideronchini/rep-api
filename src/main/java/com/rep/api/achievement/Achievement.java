package com.rep.api.achievement;

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
@Table(name = "achievements")
public class Achievement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @Column(name = "emoji_id")
    private Long emojiId;

    @ManyToOne
    @JoinColumn(name = "emoji_id", insertable = false, updatable = false)
    private Emoji emoji;

    @ManyToMany()
    @JoinTable(
            name = "users_achievements",
            joinColumns = @JoinColumn(name = "achievement_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime date;

    public void addUser(User user) {
        users.add(user);

        user.getAchievements().add(this);
    }
}
