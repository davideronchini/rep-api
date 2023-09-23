package com.rep.api.post.tag;

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
@RequestMapping("/rep-api/v1/posts/tags")
@RequiredArgsConstructor
public class PostTagController {

    private final PostTagService postTagService;

    private final UserRepository userRepository;

    @GetMapping("/all")
    public ResponseEntity<List<PostTag>> findAll() {
        return ResponseEntity.ok(postTagService.findAll());
    }

    @GetMapping("/find-by-post")
    public ResponseEntity<Set<PostTag>> findAllByPostId(@RequestParam Long postId) {
        return ResponseEntity.ok(postTagService.findAllByPostId(postId));
    }

    @GetMapping("/find-by-user")
    public ResponseEntity<Set<PostTag>> findAllByUserId(@RequestParam Long userId) {
        return ResponseEntity.ok(postTagService.findAllByUserId(userId));
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody PostTag postTag) {
        postTagService.save(postTag);

        return ResponseEntity.ok("Post created successfully");
    }

    @GetMapping("/map")
    public ResponseEntity<String> getMap(@RequestParam Long userId) throws JsonProcessingException {
        // Find the authenticated user
        User authenticatedUser = userRepository.findById(userId).orElse(null);

        if (authenticatedUser == null) {
            // If user is not found, return a 404 response
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        ObjectMapper objectMapper = new ObjectMapper();

        // Initialize lists to store nodes and edges
        List<Map<String, Object>> nodes = new ArrayList<>();
        List<Map<String, Object>> edges = new ArrayList<>();

        Map<String, Object> data = new HashMap<>();

        // Helper function to create a node map
        BiConsumer<User, Map<String, Object>> createNode = (user, nodeMap) -> {
            if (user != null) {
                // Populate node map with user details
                nodeMap.put("id", user.getId());
                nodeMap.put("firstname", user.getFirstName());
                nodeMap.put("lastname", user.getLastName());
                nodeMap.put("email", user.getEmail());

                // Convert image to Base64
                byte[] imageBytes = user.getImage();
                String base64Image = (imageBytes != null)
                        ? Base64.getEncoder().encodeToString(imageBytes)
                        : ""; // Provide a suitable default image placeholder
                nodeMap.put("image", base64Image);

                nodeMap.put("tag", user.getTag());
            }
        };

        // Add the authenticated user as a node
        Map<String, Object> authenticatedUserNode = new HashMap<>();
        createNode.accept(authenticatedUser, authenticatedUserNode);
        nodes.add(authenticatedUserNode);

        // Find all post tags associated with the authenticated user
        Set<PostTag> postTags = postTagService.findAllByUserId(authenticatedUser.getId());

        for (PostTag postTag : postTags) {
            User taggedUser = postTag.getUser();

            if (taggedUser != null && !Objects.equals(taggedUser.getId(), authenticatedUser.getId())) {
                // Check if tagged user is already in the list of nodes
                boolean isTaggedUserInNodes = nodes.stream().anyMatch(node -> node.get("id").equals(taggedUser.getId()));

                if (!isTaggedUserInNodes) {
                    // Create a node for the tagged user
                    Map<String, Object> taggedUserNode = new HashMap<>();
                    createNode.accept(taggedUser, taggedUserNode);
                    nodes.add(taggedUserNode);
                }

                // Check if the edge already exists
                boolean isEdgeExists = edges.stream()
                        .anyMatch(edge -> edge.get("from").equals(authenticatedUser.getId()) && edge.get("to").equals(taggedUser.getId()));

                if (!isEdgeExists) {
                    // Create an edge between the authenticated user and the tagged user
                    Map<String, Object> edge = new HashMap<>();
                    edge.put("from", authenticatedUser.getId());
                    edge.put("to", taggedUser.getId());
                    edges.add(edge);
                }

            }
        }

        // Fill in missing nodes from random users
        List<Map<String, Object>> newNodes = new ArrayList<>();

        if (nodes.size() < 60) {
            for (Map<String, Object> node : nodes) {
                User taggedUser = userRepository.findById((Long) node.get("id")).orElse(null);

                if (taggedUser != null) {
                    Set<PostTag> taggedUserPostTags = postTagService.findAllByUserId(taggedUser.getId());

                    for (PostTag taggedUserPostTag : taggedUserPostTags) {
                        User postTaggedUser = taggedUserPostTag.getUser();

                        if (postTaggedUser != null && !Objects.equals(postTaggedUser.getId(), authenticatedUser.getId()) && !Objects.equals(postTaggedUser.getId(), taggedUser.getId())) {
                            // Check if post-tagged user is already in the list of nodes
                            boolean isPostTaggedUserInNodes = nodes.stream().anyMatch(n -> n.get("id").equals(postTaggedUser.getId()));

                            if (!isPostTaggedUserInNodes) {
                                // Create a node for the post-tagged user
                                Map<String, Object> postTaggedUserNode = new HashMap<>();
                                createNode.accept(postTaggedUser, postTaggedUserNode);
                                newNodes.add(postTaggedUserNode);
                            }

                            // Check if the edge already exists
                            boolean isEdgeExists = edges.stream()
                                    .anyMatch(edge -> edge.get("from").equals(taggedUser.getId()) && edge.get("to").equals(postTaggedUser.getId()));

                            if (!isEdgeExists) {
                                // Create an edge between the tagged user and the post-tagged user
                                Map<String, Object> postTagEdge = new HashMap<>();
                                postTagEdge.put("from", taggedUser.getId());
                                postTagEdge.put("to", postTaggedUser.getId());
                                edges.add(postTagEdge);
                            }
                        }
                    }
                }
            }
        }

        // Add new nodes to the main list
        nodes.addAll(newNodes);

        // Populate data map with nodes and edges
        data.put("nodes", nodes);
        data.put("edges", edges);

        // Return the response in JSON format
        return ResponseEntity.ok(objectMapper.writeValueAsString(data));
    }
}