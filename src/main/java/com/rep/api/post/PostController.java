package com.rep.api.post;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rep-api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;


    @GetMapping("/all")
    public ResponseEntity<List<Post>> findAll() {
        return ResponseEntity.ok(postService.findAll());
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody Post post) {
        postService.save(post);

        return ResponseEntity.ok("Post created successfully");
    }
}
