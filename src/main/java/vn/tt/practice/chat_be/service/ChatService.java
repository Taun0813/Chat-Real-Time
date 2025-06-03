package vn.tt.practice.chat_be.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.tt.practice.chat_be.models.ChatMessage;
import vn.tt.practice.chat_be.repository.ChatMessageRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatMessageRepository chatMessageRepository;

    public ChatMessage saveMessage(ChatMessage chatMessage) {
        return chatMessageRepository.save(chatMessage);
    }

    public List<ChatMessage> getMessagesByRoom(String room) {
        return chatMessageRepository.findByRoomIdOrderByTimestampAsc(room);
    }

    public List<ChatMessage> getRecentMessages(String room, int limit) {
        return chatMessageRepository.findTopNByRoomIdOrderByTimestampDesc(room, limit);
    }

    public Long getMessageCount() {
        return chatMessageRepository.count();
    }
}
