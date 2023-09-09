package com.rep.api.medal;

import com.rep.api.user.User;
import com.rep.api.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedalServiceImpl implements MedalService {

    private final UserRepository userRepository;

    private final MedalRepository medalRepository;

    private final MedalTypeRepository medalTypeRepository;

    @Override
    public void save(Medal medal) {
        User creator = userRepository.findById(medal.getCreatorId()).orElse(null);
        User user = userRepository.findById(medal.getUserId()).orElse(null);
        MedalType medalType = medalTypeRepository.findById(medal.getMedalTypeId()).orElse(null);

        medal.setCreator(creator);
        medal.setUser(user);
        medal.setMedalType(medalType);

        medalRepository.save(medal);
    }
}
