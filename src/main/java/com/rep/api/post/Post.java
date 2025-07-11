package com.rep.api.post;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rep.api.post.reaction.PostReaction;
import com.rep.api.post.tag.PostTag;
import com.rep.api.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@JsonIgnoreProperties({"tags", "reactions", "creator"})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    private int likes = 0;

    private int views = 0;

    @Column(name = "creator_id")
    private Long creatorId;

    @ManyToOne
    @JoinColumn(name = "creator_id", insertable = false, updatable = false)
    private User creator;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime date;

    @OneToMany(mappedBy = "post")
    private Set<PostTag> tags;

    @OneToMany(mappedBy = "post")
    private Set<PostReaction> reactions;
}