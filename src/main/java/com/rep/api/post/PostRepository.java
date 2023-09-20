package com.rep.api.post;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface PostRepository extends JpaRepository<Post, Long> {

    Set<Post> findByCreatorId(Long creatorId);
}