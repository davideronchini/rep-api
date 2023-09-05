package com.rep.api.tag;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostTagServiceImpl implements PostTagService{

    private final PostTagRepository postTagRepository;
}
