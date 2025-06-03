package vn.tt.practice.chat_be.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.tt.practice.chat_be.models.User;
import vn.tt.practice.chat_be.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> getOnlineUsers() {
        return userRepository.findByIsOnlineTrue();
    }

    public List<User> getUsersInRoom(String roomId) {
        return userRepository.findByCurrentRoom(roomId);
    }

    public void setUserOnline(String username, boolean isOnline) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setIsOnline(isOnline);
            user.setLastSeen(LocalDateTime.now());
            userRepository.save(user);
        }

    }

    public void setUserRoom(String username, String roomId) {
        userRepository.updateUserCurrentRoom(username, roomId);
    }

    public User createOrUpdateUser(String username, String displayName) {
        Optional<User> existingUser = userRepository.findByUsername(username);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setDisplayName(displayName);
            user.setIsOnline(true);
            user.setLastSeen(LocalDateTime.now());
            return userRepository.save(user);
        }else {
            return userRepository.save(new User(username, displayName));
        }
    }
}
