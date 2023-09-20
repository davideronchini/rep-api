package com.rep.api.tag;

import com.rep.api.post.Post;
import com.rep.api.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface PostTagRepository extends JpaRepository<PostTag, PostTagKey> {

    Optional<PostTag> findByUserAndPost(User user, Post post);

    @Query(value = "SELECT pt FROM PostTag pt WHERE pt.id.postId = :postId")
    Set<PostTag> findAllByPostId(@Param("postId") Long postId);
}
