package stomp.example;

import java.util.Random;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRoom {
	private String channelId;
	private String ownerName;
	public static ChatRoom create(String ownerName) {
		ChatRoom chatRoom = new ChatRoom();
		chatRoom.channelId = generateRandomChannelId();
		chatRoom.ownerName = ownerName;
		return chatRoom;
	}
	private static String generateRandomChannelId() {
		Random random = new Random();
		return String.valueOf(random.nextInt(10000));
	}
}

