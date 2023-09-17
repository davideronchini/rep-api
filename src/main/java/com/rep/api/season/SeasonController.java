package com.rep.api.season;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rep-api/v1/seasons")
@RequiredArgsConstructor
public class SeasonController {

    private final SeasonService seasonService;

    @GetMapping("/all")
    public ResponseEntity<List<Season>> findAll() {
        return ResponseEntity.ok(seasonService.findAll());
    }

    @PostMapping("/create")
    public ResponseEntity<String> create() {
        seasonService.save();

        return ResponseEntity.ok("Season created successfully");
    }
}
