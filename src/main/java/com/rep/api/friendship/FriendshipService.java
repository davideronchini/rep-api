package com.rep.api.friendship;

import com.rep.api.user.User;

import java.util.List;

public interface FriendshipService {

    List<Friendship> findAllByCreator(User creator);

    List<Friendship> findAllByReceiver(User receiver);

    void save(Friendship friendship);

    boolean validate(Friendship friendship);
}
