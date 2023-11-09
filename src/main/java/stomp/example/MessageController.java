package stomp.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MessageController {
    private final SimpMessageSendingOperations simpMessageSendingOperations;

    @MessageMapping("/message")
    public void message(Message message) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = now.format(formatter);
        message.setTime(formattedTime);
        simpMessageSendingOperations.convertAndSend("/sub/" + message.getChannelId(), message);
    }
}
