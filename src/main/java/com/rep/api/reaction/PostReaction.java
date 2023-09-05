package com.rep.api.reaction;

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

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "post_reactions")
public class PostReaction {

    @EmbeddedId
    PostReactionKey id;

    private String comment;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime date;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("postId")
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @MapsId("emojiId")
    @JoinColumn(name = "emoji_id")
    private Emoji emoji;
}

@Embeddable
class PostReactionKey implements Serializable {

    @Column(name = "user_id")
    Long userId;

    @Column(name = "post_id")
    Long postId;

    @Column(name = "emoji_id")
    Long emojiId;
}
