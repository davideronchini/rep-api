package com.rep.api.post.tag;

import com.rep.api.post.Post;
import com.rep.api.post.PostRepository;
import com.rep.api.user.User;
import com.rep.api.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PostTagServiceImpl implements PostTagService {

    private final PostTagRepository postTagRepository;

    private final UserRepository userRepository;

    private final PostRepository postRepository;

    @Override
    public List<PostTag> findAll() {
        return postTagRepository.findAll();
    }

    @Override
    public Set<PostTag> findAllByUserId(Long userId) {
        Set<PostTag> tags = new HashSet<>();

        Set<Post> posts = postRepository.findByCreatorId(userId);
        if (!posts.isEmpty()) {
            posts.forEach(post -> tags.addAll(postTagRepository.findAllByPostId(post.getId())));

            return tags;
        }

        return tags;
    }

    @Override
    public Set<PostTag> findAllByPostId(Long postId) {
        return postTagRepository.findAllByPostId(postId);
    }

    @Override
    public void save(PostTag postTag) {
        User user = userRepository.findById(postTag.getId().getUserId()).orElse(null);
        Post post = postRepository.findById(postTag.getId().getPostId()).orElse(null);

        if (user != null && post != null) {
            PostTag postTagFound = postTagRepository.findByUserAndPost(user, post).orElse(null);

            if (postTagFound == null) {
                postTag.setUser(user);
                postTag.setPost(post);

                postTagRepository.save(postTag);
            }
        }
    }
}