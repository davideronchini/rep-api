package com.rep.api.friend;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService{

    private final FriendRepository friendRepository;

    @Override
    public void save(Friend friend) {
        friendRepository.save(friend);
    }
}
