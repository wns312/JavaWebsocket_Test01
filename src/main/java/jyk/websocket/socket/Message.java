package jyk.websocket.socket;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class Message {
    private int roomId;
    private String username;
    private String content;
}
