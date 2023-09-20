package com.rep.api.tag;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/rep-api/v1/posts/tags")
@RequiredArgsConstructor
public class PostTagController {

    private final PostTagService postTagService;

    @GetMapping("/all")
    public ResponseEntity<List<PostTag>> findAll() {
        return ResponseEntity.ok(postTagService.findAll());
    }

    @GetMapping("/")
    public ResponseEntity<Set<PostTag>> findAllByPostId(@RequestParam Long postId) {
        return ResponseEntity.ok(postTagService.findAllByPostId(postId));
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody PostTag postTag) {
        postTagService.save(postTag);

        return ResponseEntity.ok("Post created successfully");
    }
}