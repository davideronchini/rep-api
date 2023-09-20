package com.rep.api.reaction;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/rep-api/v1/posts/reactions")
@RequiredArgsConstructor
public class PostReactionController {

    private final PostReactionService postReactionService;

    @GetMapping("/all")
    public ResponseEntity<List<PostReaction>> findAll() {
        return ResponseEntity.ok(postReactionService.findAll());
    }

    @GetMapping("/")
    public ResponseEntity<Set<PostReaction>> findAllByPostId(@RequestParam Long postId) {
        return ResponseEntity.ok(postReactionService.findAllByPostId(postId));
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody PostReaction postReaction) {
        postReactionService.save(postReaction);

        return ResponseEntity.ok("Post created successfully");
    }
}