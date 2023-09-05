package com.rep.api.reaction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostReactionServiceImpl implements PostReactionService{

    private final PostReactionRepository postReactionRepository;
}
