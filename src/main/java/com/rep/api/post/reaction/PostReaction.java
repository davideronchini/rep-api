package com.rep.api.post.reaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rep.api.emoji.Emoji;
import com.rep.api.post.Post;
import com.rep.api.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@JsonIgnoreProperties({"user", "post", "emoji"})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "post_reactions")
public class PostReaction {

    @EmbeddedId
    private PostReactionKey id;

    private String comment;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime date;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @MapsId("postId")
    @JoinColumn(name = "post_id", insertable = false, updatable = false)
    private Post post;

    @ManyToOne
    @MapsId("emojiId")
    @JoinColumn(name = "emoji_id", insertable = false, updatable = false)
    private Emoji emoji;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
class PostReactionKey implements Serializable {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "post_id")
    private Long postId;

    @Column(name = "emoji_id")
    private Long emojiId;
}