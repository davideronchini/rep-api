package com.rep.api.interaction;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rep-api/v1/interactions")
@RequiredArgsConstructor
public class InteractionController {

    private final InteractionService interactionService;

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody Interaction interaction) {
        interactionService.createInteraction(interaction);

        return ResponseEntity.ok("Interaction created successfully");
    }
}
