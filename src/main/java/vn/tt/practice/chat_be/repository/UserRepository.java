package vn.tt.practice.chat_be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import vn.tt.practice.chat_be.models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    List<User> findByIsOnlineTrue();

    List<User> findByCurrentRoom(String roomId);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.isOnline = :isOnline WHERE u.username = :username")
    void updateUserOnlineStatus(@Param("username") String username, @Param("isOnline") Boolean isOnline);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.currentRoom = :roomId WHERE u.username = :username")
    void updateUserCurrentRoom(@Param("username") String username, @Param("roomId") String roomId);
}
