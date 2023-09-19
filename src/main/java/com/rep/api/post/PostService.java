package com.rep.api.post;

import java.util.List;

public interface PostService {

    List<Post> findAll();

    void save(Post post);
}