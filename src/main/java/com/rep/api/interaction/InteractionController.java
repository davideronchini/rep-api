package com.rep.api.interaction;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/rep-api/v1/interactions")
@RequiredArgsConstructor
public class InteractionController {

    private final InteractionService interactionService;

    @GetMapping("/")
    public ResponseEntity<List<Interaction>> findAll() {
        return ResponseEntity.ok(interactionService.findAll());
    }

    @GetMapping("/all-visible")
    public ResponseEntity<Set<Optional<Interaction>>> findAllVisible() {
        return ResponseEntity.ok(interactionService.findAllVisible());
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody Interaction interaction) {
        interactionService.save(interaction);

        return ResponseEntity.ok("Interaction created successfully");
    }
}
