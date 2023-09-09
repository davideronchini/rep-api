package com.rep.api.medal;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedalTypeServiceImpl implements MedalTypeService {

    private final MedalTypeRepository medalTypeRepository;

    @Override
    public void save(MedalType medalType) {
        medalTypeRepository.save(medalType);
    }
}
