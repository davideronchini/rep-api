package com.rep.api.reaction;

import com.rep.api.post.Post;
import com.rep.api.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface PostReactionRepository extends JpaRepository<PostReaction, PostReactionKey> {

    Optional<PostReaction> findByUserAndPost(User user, Post post);

    @Query(value = "SELECT pr FROM PostReaction pr WHERE pr.id.postId = :postId")
    Set<PostReaction> findAllByPostId(@Param("postId") Long postId);
}