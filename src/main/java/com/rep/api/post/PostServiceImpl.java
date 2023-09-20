package com.rep.api.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Set<Post> findByCreatorId(Long creatorId) {
        return postRepository.findByCreatorId(creatorId);
    }

    @Override
    public void save(Post post) {
        postRepository.save(post);
    }
}