package com.rep.api.reaction;

import java.util.List;
import java.util.Set;

public interface PostReactionService {

    List<PostReaction> findAll();

    Set<PostReaction> findAllByPostId(Long postId);

    void save(PostReaction postReaction);
}