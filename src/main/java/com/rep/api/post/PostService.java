package com.rep.api.post;

import java.util.List;
import java.util.Set;

public interface PostService {

    List<Post> findAll();

    Set<Post> findByCreatorId(Long creatorId);

    void save(Post post);
}