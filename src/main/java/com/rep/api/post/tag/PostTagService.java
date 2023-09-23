package com.rep.api.post.tag;

import java.util.List;
import java.util.Set;

public interface PostTagService {

    List<PostTag> findAll();

    Set<PostTag> findAllByUserId(Long userId);

    Set<PostTag> findAllByPostId(Long postId);

    void save(PostTag postTag);
}