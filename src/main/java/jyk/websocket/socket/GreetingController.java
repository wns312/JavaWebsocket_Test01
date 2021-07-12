package jyk.websocket.socket;

import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class GreetingController {

    public SimpMessagingTemplate simpMessagingTemplate;

    // /api/hello로 온 메시지를 /topic/test로 보낸다
    @MessageMapping("/hello") // /app/hello
    @SendTo("/topic/greetings")
    public Message greeting(Message message) throws Exception {
        System.out.println("개짜증나네..");
        System.out.println(message);
        Message newMessage = new Message(0, message.getUsername(), message.getContent());
        System.out.println(newMessage.getUsername()+ newMessage.getContent());
        return newMessage;
    }
    // 특정 방에 보내기 (이걸로 특정 채널로 보내기도 가능해짐)
    @MessageMapping("/hello2/{id}/{id2}") // /app/hello2
    public void greeting2(Message message, @DestinationVariable String id2, @DestinationVariable String id) throws Exception {
        System.out.println(id);
        System.out.println(id2);
        Message newMessage = new Message(0, message.getUsername(), message.getContent());
        System.out.println(newMessage.getUsername()+ newMessage.getContent());
    }

}
