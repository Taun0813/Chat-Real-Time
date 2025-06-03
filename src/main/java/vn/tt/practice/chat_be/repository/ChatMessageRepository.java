package vn.tt.practice.chat_be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.tt.practice.chat_be.models.ChatMessage;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    List<ChatMessage> findByRoomIdOrderByTimestampAsc(String roomId);

    @Query("SELECT m FROM ChatMessage m WHERE m.roomId = :roomId ORDER BY m.timestamp DESC")
    List<ChatMessage> findRecentMessagesByRoomId(@Param("roomId") String roomId);

    @Query(value = "SELECT * FROM chat_messages WHERE room_id = :roomId ORDER BY timestamp DESC LIMIT :limit", nativeQuery = true)
    List<ChatMessage> findTopNByRoomIdOrderByTimestampDesc(@Param("roomId") String roomId, @Param("limit") int limit);
}
