package com.rep.api.customization;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rep-api/v1/customizations")
@RequiredArgsConstructor
public class CustomizationController {

    private final CustomizationService customizationService;

    @GetMapping("/all")
    public ResponseEntity<List<Customization>> findAll() {
        return ResponseEntity.ok(customizationService.findAll());
    }

    @GetMapping("/")
    public ResponseEntity<Optional<Customization>> findByUserId(@RequestParam Long userId) {
        return ResponseEntity.ok(customizationService.findByUserId(userId));
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody Customization customization) {
        customizationService.save(customization);

        return ResponseEntity.ok("Post created successfully");
    }
}