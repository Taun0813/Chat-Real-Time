package vn.tt.practice.chat_be.controller;


import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import vn.tt.practice.chat_be.models.ChatMessage;
import vn.tt.practice.chat_be.models.MessageType;
import vn.tt.practice.chat_be.service.ChatService;
import vn.tt.practice.chat_be.service.UserService;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    private final SimpMessagingTemplate messagingTemplate;

    private final ChatService chatService;

    private final UserService userService;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage message) {
        logger.info("Received message: {} from {}",message.getContent(), message.getSender());

        ChatMessage savedMessage = chatService.saveMessage(message);

        return savedMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage message, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", message.getSender());
        headerAccessor.getSessionAttributes().put("roomId", message.getRoomId());

        logger.info("User {} joined room {}",message.getSender(),message.getRoomId());

        sendUserCountUpdate(message.getRoomId());

        return message;
    }

    @MessageMapping("/chat.typing")
    public void handleTyping(@Payload ChatMessage message) {
        logger.info("User {} is typing in room {}", message.getSender(), message.getRoomId());

        messagingTemplate.convertAndSend("/topic/typing" + message.getRoomId());
    }

    @MessageMapping("/chat.stopTyping")
    public void handleStopTyping(@Payload ChatMessage message) {
        logger.info("User {} stopped typing in room {}", message.getSender(), message.getRoomId());

        messagingTemplate.convertAndSend("/topic/stopTyping/" + message.getRoomId());
    }

    private void sendUserCountUpdate(String roomId) {
        int userCount = userService.getUsersInRoom(roomId).size();

        ChatMessage userCountMessage = new ChatMessage();
        userCountMessage.setType(MessageType.JOIN);
        userCountMessage.setContent("USER_COUNT_UPDATE");
        userCountMessage.setSender("SYSTEM");
        userCountMessage.setRoomId(roomId);

        messagingTemplate.convertAndSend("topic/userCount/" + roomId, userCount);
    }

}
