package vn.tt.practice.chat_be.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vn.tt.practice.chat_be.models.ChatMessage;
import vn.tt.practice.chat_be.models.User;
import vn.tt.practice.chat_be.service.ChatService;
import vn.tt.practice.chat_be.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class MessageController {

    private final ChatService chatService;
    private final UserService userService;

    @GetMapping("/messages/{roomId}")
    public ResponseEntity<List<ChatMessage>> getMessages(@PathVariable String roomId,
                                                         @RequestParam(defaultValue = "50") int limit) {
        List<ChatMessage> messages = chatService.getRecentMessages(roomId, limit);
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/users/online")
    public ResponseEntity<List<User>> getOnlineUsers() {
        List<User> onlineUsers = userService.getOnlineUsers();
        return ResponseEntity.ok(onlineUsers);
    }

    @GetMapping("/users/room/{roomId}")
    public ResponseEntity<List<User>> getUsersInRoom(@PathVariable String roomId) {
        List<User> usersInRoom = userService.getUsersInRoom(roomId);
        return ResponseEntity.ok(usersInRoom);
    }

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalMessages", chatService.getMessageCount());
        stats.put("onlineUsers", userService.getOnlineUsers().size());
        return ResponseEntity.ok(stats);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createOrUpdateUser(user.getUsername(), user.getDisplayName());
        return ResponseEntity.ok(createdUser);
    }
}
