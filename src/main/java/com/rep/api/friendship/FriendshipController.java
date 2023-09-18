package com.rep.api.friendship;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rep.api.user.User;
import com.rep.api.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.function.BiConsumer;

@RestController
@RequestMapping("/rep-api/v1/friendships")
@RequiredArgsConstructor
public class FriendshipController {

    private final FriendshipService friendshipService;

    private final UserRepository userRepository;

    @GetMapping("/map")
    public ResponseEntity<String> getMap(@RequestParam Long userId) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        User authenticatedUser = userRepository.findById(userId).orElse(null);

        if (authenticatedUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Handle case where authenticatedUser is not found
        }

        List<Friendship> friendships = friendshipService.findAllByCreator(authenticatedUser);

        Map<String, Object> data = new HashMap<>();
        List<Map<String, Object>> nodes = new ArrayList<>();
        List<Map<String, Object>> edges = new ArrayList<>();

        // Helper function to create a node map
        BiConsumer<User, Map<String, Object>> createNode = (user, nodeMap) -> {
            nodeMap.put("id", user.getId());
            nodeMap.put("firstname", user.getFirstName());
            nodeMap.put("lastname", user.getLastName());
            nodeMap.put("email", user.getEmail());
            nodeMap.put("tag", user.getTag());
        };

        // Create node for authenticated user
        Map<String, Object> authenticatedUserNode = new HashMap<>();
        createNode.accept(authenticatedUser, authenticatedUserNode);
        nodes.add(authenticatedUserNode);

        for (Friendship friendship : friendships) {
            if (friendshipService.validate(friendship)) {
                // Create node for friend
                Map<String, Object> friendNode = new HashMap<>();
                createNode.accept(friendship.getReceiver(), friendNode);
                nodes.add(friendNode);

                // Create edge between authenticated user and friend
                Map<String, Object> edge = new HashMap<>();
                edge.put("from", authenticatedUser.getId());
                edge.put("to", friendship.getReceiver().getId());
                edges.add(edge);

                List<Friendship> friendFriendships = friendshipService.findAllByReceiver(friendship.getReceiver());

                for (Friendship friendFriendship : friendFriendships) {
                    if (friendshipService.validate(friendFriendship)) {
                        User friendOfFriend = friendFriendship.getCreator().equals(friendship.getReceiver()) ? friendFriendship.getReceiver() : friendFriendship.getCreator();
                        if (!friendOfFriend.equals(authenticatedUser)) {
                            Map<String, Object> edgeFriend = new HashMap<>();
                            edgeFriend.put("from", friendOfFriend.getId());
                            edgeFriend.put("to", friendship.getReceiver().getId());
                            edges.add(edgeFriend);
                        }
                    }
                }
            }
        }

        data.put("nodes", nodes);
        data.put("edges", edges);

        // Return the response in JSON format
        return ResponseEntity.ok(objectMapper.writeValueAsString(data));
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody Friendship friendship) {
        friendshipService.save(friendship);

        return ResponseEntity.ok("Friend created successfully");
    }
}
