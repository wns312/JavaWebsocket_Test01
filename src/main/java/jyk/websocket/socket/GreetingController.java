package jyk.websocket.socket;

import lombok.Data;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {
    // /api/hello로 온 메시지를 /topic/test로 보낸다
    @MessageMapping("/hello") // /app/hello
    @SendTo("/topic/greetings")
    public Message greeting(Message message) throws Exception {
        System.out.println("개짜증나네..");
        System.out.println(message);
        Message newMessage = new Message(message.getUsername(), message.getContent());
        System.out.println(newMessage.getUsername()+ newMessage.getContent());
        return newMessage;
    }

}
