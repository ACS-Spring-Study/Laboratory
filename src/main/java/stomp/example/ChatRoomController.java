package stomp.example;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class ChatRoomController {

    @PostMapping("/room")
    @ResponseBody
    public ChatRoom createRoom(@RequestParam String ownerName) {
        return ChatRoom.createRoom(ownerName);
    }
}
