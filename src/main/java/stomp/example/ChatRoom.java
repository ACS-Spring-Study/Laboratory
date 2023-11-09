package stomp.example;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRoom {

    private String ownerName;
    private String channelId;

    public static ChatRoom createRoom(String ownerName) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.ownerName = ownerName;
        chatRoom.channelId = UUID.randomUUID().toString();
        return chatRoom;
    }

}
