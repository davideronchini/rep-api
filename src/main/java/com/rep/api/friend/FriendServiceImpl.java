package com.rep.api.friend;

import com.rep.api.user.User;
import com.rep.api.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService{

    private final UserRepository userRepository;

    private final FriendRepository friendRepository;

    @Override
    public void save(Friend friend) {
        User creator = userRepository.findById(friend.getId().getCreatorId()).orElse(null);
        User receiver = userRepository.findById(friend.getId().getReceiverId()).orElse(null);

        friend.setCreator(creator);
        friend.setReceiver(receiver);

        friendRepository.save(friend);
    }
}
