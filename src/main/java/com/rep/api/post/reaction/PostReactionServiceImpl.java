package com.rep.api.post.reaction;

import com.rep.api.emoji.Emoji;
import com.rep.api.emoji.EmojiRepository;
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
public class PostReactionServiceImpl implements PostReactionService {

    private final PostReactionRepository postReactionRepository;

    private final UserRepository userRepository;

    private final PostRepository postRepository;

    private final EmojiRepository emojiRepository;

    @Override
    public List<PostReaction> findAll() {
        return postReactionRepository.findAll();
    }

    @Override
    public Set<PostReaction> findAllByPostId(Long postId) {
        return postReactionRepository.findAllByPostId(postId);
    }

    @Override
    public void save(PostReaction postReaction) {
        User user = userRepository.findById(postReaction.getId().getUserId()).orElse(null);
        Post post = postRepository.findById(postReaction.getId().getPostId()).orElse(null);
        Emoji emoji = emojiRepository.findById(postReaction.getId().getEmojiId()).orElse(null);

        if (user != null && post != null && emoji != null) {
            PostReaction postReactionFound = postReactionRepository.findByUserAndPost(user, post).orElse(null);

            if (postReactionFound != null) {
                // Update reaction (there can only be one reaction per user)
                postReactionFound.setComment(postReaction.getComment());
                postReactionFound.setDate(postReaction.getDate());
                postReactionRepository.save(postReactionFound);
            } else {
                // Create the reaction
                postReaction.setUser(user);
                postReaction.setPost(post);
                postReaction.setEmoji(emoji);
                postReactionRepository.save(postReaction);
            }
        }
    }
}