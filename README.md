# 💬 Chat Real-Time

Ứng dụng chat thời gian thực được xây dựng với Spring Boot và H2 Database, cho phép người dùng trò chuyện trực tiếp với nhau một cách mượt mà và nhanh chóng.

## ✨ Tính năng

- 💬 **Chat thời gian thực** - Trò chuyện tức thời không độ trễ
- 👥 **Hỗ trợ nhiều người dùng** - Chat nhóm và chat riêng tư
- 🔔 **Thông báo tin nhắn mới** - Nhận thông báo khi có tin nhắn mới
- 📱 **Giao diện responsive** - Tương thích trên mọi thiết bị
- 🎨 **UI/UX thân thiện** - Giao diện đẹp mắt, dễ sử dụng
- 🔒 **Bảo mật tin nhắn** - Xác thực và phân quyền người dùng
- 📸 **Chia sẻ hình ảnh** - Gửi và nhận hình ảnh trong chat
- 😊 **Emoji và reactions** - Thể hiện cảm xúc với emoji

## 🛠️ Công nghệ sử dụng

### Backend
- **Java 17+**
- **Spring Boot 3.x**
- **Spring WebSocket** - Real-time communication
- **Spring Security** - Authentication & Authorization
- **Spring Data JPA** - Database operations
- **H2 Database** - In-memory database
- **Maven** - Dependency management

### Frontend
- **HTML5**
- **CSS3**
- **JavaScript (ES6+)**
- **Bootstrap** (nếu có)
- **SockJS** & **STOMP** - WebSocket client

### Real-time Communication
- **Spring WebSocket**
- **STOMP Protocol**
- **SockJS** fallback

## 📋 Yêu cầu hệ thống

- **Java** >= 17
- **Maven** >= 3.6.0
- **IDE**: IntelliJ IDEA, Eclipse, hoặc VS Code

## 🚀 Cài đặt và chạy dự án

### 1. Clone repository

```bash
git clone https://github.com/Taun0813/Chat-Real-Time.git
cd Chat-Real-Time
```

### 2. Cài đặt dependencies

```bash
mvn clean install
```

### 3. Cấu hình ứng dụng

File `application.properties` hoặc `application.yml`:

```properties
# Server Configuration
server.port=8080

# H2 Database Configuration
spring.datasource.url=jdbc:h2:mem:chatdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password

# H2 Console (for development)
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# JPA Configuration
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# WebSocket Configuration
spring.websocket.sockjs.heartbeat.time=25000
```

### 4. Chạy ứng dụng

**Sử dụng Maven:**
```bash
mvn spring-boot:run
```

**Hoặc chạy từ IDE:**
- Import project vào IDE
- Chạy main class `ChatRealtimeApplication.java`

**Sử dụng JAR file:**
```bash
mvn clean package
java -jar target/chat-realtime-0.0.1-SNAPSHOT.jar
```

Ứng dụng sẽ chạy tại: [http://localhost:8080](http://localhost:8080)

**H2 Database Console:** [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

## 📁 Cấu trúc dự án

```
Chat-Real-Time/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/chatapp/
│   │   │       ├── ChatRealtimeApplication.java
│   │   │       ├── config/
│   │   │       │   ├── CorsConfig.java
│   │   │       │   └── WebSocketConfig.java
│   │   │       ├── controller/
│   │   │       │   ├── ChatController.java
│   │   │       │   ├── MessageController.java
│   │   │       ├── model/
│   │   │       │   ├── ChatMessage.java
│   │   │       │   ├── MessageType.java
│   │   │       │   └── User.java
│   │   │       ├── repository/
│   │   │       │   ├── UserRepository.java
│   │   │       │   ├── ChatMessageRepository.java
│   │   │       └── service/
│   │   │           ├── UserService.java
│   │   │           ├── ChatService.java
│   │   └── resources/
│   │       ├── ui/
│   │       │   ├── index.html
│   │       └── application.properties
│   └── test/
├── pom.xml
└── README.md
```

## 🔧 Dependencies chính

```xml
<dependencies>
    <!-- Spring Boot Starters -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-websocket</artifactId>
    </dependency>
    
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>

    <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
    </dependency>
    
    <!-- H2 Database -->
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>
    
    <!-- WebJars for frontend libraries -->
    <dependency>
        <groupId>org.webjars</groupId>
        <artifactId>sockjs-client</artifactId>
    </dependency>
    
    <dependency>
        <groupId>org.webjars</groupId>
        <artifactId>stomp-websocket</artifactId>
    </dependency>
</dependencies>
```

## 🎯 Hướng dẫn sử dụng

1. **Khởi động ứng dụng**: Chạy `mvn spring-boot:run`

2. **Truy cập ứng dụng**: Mở trình duyệt và truy cập `http://localhost:8080`

3. **Xem database** (development): 
   - Truy cập H2 Console tại `http://localhost:8080/h2-console`
   - JDBC URL: `jdbc:h2:mem:chatdb`
   - Username: `sa`, Password: `password`

4. **Đăng ký/Đăng nhập**: 
   - Tạo tài khoản mới hoặc đăng nhập
   - Xác thực thông tin

5. **Bắt đầu chat**:
   - Tham gia phòng chat hoặc tạo phòng mới
   - Gửi tin nhắn real-time với WebSocket
   - Sử dụng emoji và chia sẻ hình ảnh

## 🧪 Testing

```bash
# Chạy unit tests
mvn test

# Chạy integration tests  
mvn verify

# Test coverage report
mvn jacoco:report
```

## 🔧 Configuration

### WebSocket Configuration

```java
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic", "/queue");

        config.setApplicationDestinationPrefixes("/app");

        config.setUserDestinationPrefix("/user");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("http://localhost:63342")
                .withSockJS();
    }
}
```

### H2 Database Schema

Database sẽ được tự động tạo khi khởi động ứng dụng với các bảng:
- `users` - Thông tin người dùng
- `chat_rooms` - Phòng chat
- `chat_messages` - Tin nhắn
- `user_room_mapping` - Mapping user và room

## 🤝 Đóng góp

Mọi đóng góp đều được chào đón! Để đóng góp:

1. **Fork** repository này
2. **Tạo branch** mới (`git checkout -b feature/AmazingFeature`)
3. **Commit** thay đổi (`git commit -m 'Add some AmazingFeature'`)
4. **Push** lên branch (`git push origin feature/AmazingFeature`)
5. **Tạo Pull Request**

### Quy tắc đóng góp

- Tuân thủ Java coding conventions
- Viết unit tests cho các tính năng mới
- Cập nhật documentation khi cần thiết
- Sử dụng Spring Boot best practices

## 📝 License

Dự án này được phát hành dưới [MIT License](LICENSE).

## 👨‍💻 Tác giả

**Taun0813**
- GitHub: [@Taun0813](https://github.com/Taun0813)
- Email: [yentuan130803@gmail.com]

## 🙏 Acknowledgments

- Cảm ơn Spring Framework community
- Cảm ơn tất cả contributors đã đóng góp cho dự án
- Cảm ơn cộng đồng Java và Spring Boot Việt Nam

## 🐛 Báo lỗi

Nếu bạn phát hiện lỗi, vui lòng tạo [issue](https://github.com/Taun0813/Chat-Real-Time/issues) với thông tin chi tiết:

- Phiên bản Java và Spring Boot
- Log file và stack trace
- Các bước tái hiện lỗi
- Kết quả mong đợi và kết quả thực tế

## 🔄 Phiên bản

- **v1.0.0** - Phiên bản đầu tiên
  - Chat real-time với Spring WebSocket
  - Đăng ký/đăng nhập với Spring Security  
  - H2 in-memory database
  - Giao diện responsive

## 🚧 Tính năng sắp tới

- [ ] Persistent database (PostgreSQL/MySQL)
- [ ] File upload và sharing
- [ ] Private messaging
- [ ] Chat history pagination
- [ ] Online users status
- [ ] Message reactions
- [ ] Push notifications
- [ ] Docker containerization
- [ ] REST API documentation với Swagger

## 🔍 API Endpoints

### REST Endpoints
- `GET /api/messages/{roomId}?limit=50` – Lấy danh sách tin nhắn gần nhất trong phòng
  
- `GET /api/users` – Lấy danh sách tất cả users
  
- `GET /api/users/online` – Lấy danh sách người dùng đang online
  
- `GET /api/users/room/{roomId}` – Lấy danh sách người dùng trong phòng
  
- `POST /api/users` – Tạo hoặc cập nhật user
  
- `POST /api/auth/login` – Đăng nhập
  
- `POST /api/auth/register` – Đăng ký
  
- `GET /api/chatrooms` – Lấy danh sách phòng chat

- `GET /api/stats` – Lấy thông tin thống kê (số lượng tin nhắn, người online)

### WebSocket Endpoints  

- `/ws` – WebSocket connection endpoint (STOMP/WebSocket handshake endpoint)

- `/app/chat.sendMessage` – Gửi tin nhắn

- `/app/chat.addUser` – Thêm user vào phòng chat

- `/app/chat.typing` – Báo hiệu đang gõ

- `/app/chat.stopTyping` – Báo hiệu ngừng gõ

- `/topic/public` – Subscribe để nhận tin nhắn công khai

- `/topic/typing{roomId}` – Subscribe để biết ai đang gõ trong phòng

- `/topic/stopTyping/{roomId}` – Subscribe khi ai đó ngừng gõ

- `/topic/userCount/{roomId}` – Subscribe để theo dõi số người trong phòng

---

⭐ **Nếu dự án hữu ích, hãy cho một star để ủng hộ!** ⭐
