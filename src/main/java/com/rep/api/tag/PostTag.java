package com.rep.api.tag;

import com.rep.api.post.Post;
import com.rep.api.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "post_tags")
public class PostTag {

    @EmbeddedId
    PostTagKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("postId")
    @JoinColumn(name = "post_id")
    private Post post;
}

@Embeddable
class PostTagKey implements Serializable {

    @Column(name = "user_id")
    Long userId;

    @Column(name = "post_id")
    Long postId;
}
