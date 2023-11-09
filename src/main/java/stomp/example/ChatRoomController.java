package stomp.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/chat")
public class ChatRoomController {

	// 채팅방 생성
	@PostMapping("/room")
	@ResponseBody
	public ChatRoom create(@RequestBody ChatRoom body) {
		String ownerName = body.getOwnerName();
		return ChatRoom.create(ownerName);
	}
}
