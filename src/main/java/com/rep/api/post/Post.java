package com.rep.api.post;

import com.rep.api.reaction.PostReaction;
import com.rep.api.tag.PostTag;
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
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    private int likes;

    private int views;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime date;

    @OneToMany(mappedBy = "post")
    private Set<PostTag> tags;

    @OneToMany(mappedBy = "post")
    private Set<PostReaction> reactions;
}
