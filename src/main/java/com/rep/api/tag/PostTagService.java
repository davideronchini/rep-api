package com.rep.api.tag;

import java.util.List;
import java.util.Set;

public interface PostTagService {

    List<PostTag> findAll();

    Set<PostTag> findAllByPostId(Long postId);

    void save(PostTag postTag);
}