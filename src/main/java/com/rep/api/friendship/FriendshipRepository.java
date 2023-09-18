package com.rep.api.friendship;

import com.rep.api.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {

    List<Friendship> findAllByCreator(User creator);

    List<Friendship> findAllByReceiver(User receiver);

    Optional<Friendship> findByCreatorIdAndReceiverId(Long creatorId, Long receiverId);

    boolean existsByCreatorIdAndReceiverId(Long creatorId, Long receiverId);
}
