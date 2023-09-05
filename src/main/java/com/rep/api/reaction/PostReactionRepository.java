package com.rep.api.reaction;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostReactionRepository extends JpaRepository<PostReaction, PostReactionKey> {
}
