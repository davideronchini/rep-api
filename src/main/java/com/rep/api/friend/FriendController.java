package com.rep.api.friend;

import com.rep.api.interaction.InteractionDTO;
import com.rep.api.interaction.InteractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rep-api/v1/friends")
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody Friend friend) {
        friendService.save(friend);

        return ResponseEntity.ok("Friend created successfully");
    }
}
