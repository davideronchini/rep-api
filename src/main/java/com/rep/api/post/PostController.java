package com.rep.api.post;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/rep-api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/all")
    public ResponseEntity<List<Post>> findAll() {
        return ResponseEntity.ok(postService.findAll());
    }

    @GetMapping("/")
    public ResponseEntity<Set<Post>> findByCreatorId(@RequestParam Long creatorId) {
        return ResponseEntity.ok(postService.findByCreatorId(creatorId));
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody Post post) {
        postService.save(post);

        return ResponseEntity.ok("Post created successfully");
    }
}