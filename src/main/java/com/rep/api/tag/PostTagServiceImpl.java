package com.rep.api.tag;

import com.rep.api.post.Post;
import com.rep.api.post.PostRepository;
import com.rep.api.user.User;
import com.rep.api.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

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