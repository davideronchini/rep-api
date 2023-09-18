package com.rep.api.friendship;

import com.rep.api.user.User;
import com.rep.api.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendshipServiceImpl implements FriendshipService {

    private final UserRepository userRepository;

    private final FriendshipRepository friendshipRepository;

    @Override
    public List<Friendship> findAllByCreator(User creator) {
        return friendshipRepository.findAllByCreator(creator);
    }

    @Override
    public List<Friendship> findAllByReceiver(User receiver) {
        return friendshipRepository.findAllByReceiver(receiver);
    }

    @Override
    public void save(Friendship friendship) {
        User creator = userRepository.findById(friendship.getId().getCreatorId()).orElse(null);
        User receiver = userRepository.findById(friendship.getId().getReceiverId()).orElse(null);

        if (creator != null && receiver != null) {
            friendship.setCreator(creator);
            friendship.setReceiver(receiver);

            long creatorId = friendship.getId().getCreatorId();
            long receiverId = friendship.getId().getReceiverId();

            if (!friendshipRepository.existsByCreatorIdAndReceiverId(creatorId, receiverId) && !friendshipRepository.existsByCreatorIdAndReceiverId(receiverId, creatorId)) {
                friendshipRepository.save(friendship); // Friendship doesn't exist, so create it
            } else if (friendshipRepository.existsByCreatorIdAndReceiverId(receiverId, creatorId)) {
                // Accept the friend request
                Friendship friendshipFound = friendshipRepository.findByCreatorIdAndReceiverId(receiverId, creatorId).orElse(null);

                if (friendshipFound != null) {
                    friendshipFound.setAccepted(true);
                    friendshipRepository.save(friendshipFound);
                }
            }
        }
    }

    @Override
    public boolean validate(Friendship friendship) {
        User creator = friendship.getCreator();
        User receiver = friendship.getReceiver();

        if (creator == null || receiver == null) {
            return false;
        }

        Friendship friendshipFound = friendshipRepository.findByCreatorIdAndReceiverId(friendship.getId().getCreatorId(), friendship.getId().getReceiverId())
                .orElseGet(() -> friendshipRepository.findByCreatorIdAndReceiverId(friendship.getId().getReceiverId(), friendship.getId().getCreatorId())
                        .orElse(null));

        return friendshipFound != null && friendshipFound.isAccepted();
    }
}
