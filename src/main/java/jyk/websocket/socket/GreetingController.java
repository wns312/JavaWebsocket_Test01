package jyk.websocket.socket;

import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class GreetingController {

    public SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/hello") // 클라이언트에서 보내는 실제 경로는 "{접두어}/경로" 이지만, 어노테이션에서는 "/경로" 로 받는다
    @SendTo("/topic/greetings") // 전송을 할 때에는 /topic과 같이 프론트에서 구독한 경로를 풀네임으로 적어준다
    public Message greeting(Message message) throws Exception {
        return message;
    }
    // 특정 방에 보내기
    // 응용해서 5번 방의 메시지를 25번 방에 보낼 수도 있다
    @MessageMapping("/hello2/{id}/{id2}") // /app/hello2
    public void greeting2(Message message, @DestinationVariable String id2, @DestinationVariable String id) throws Exception {
        String path = "/topic/"+id; // path를 직접 만든다
        simpMessagingTemplate.convertAndSend(path, message); // 경로, 메시지 순서
    }
    // 야매로 유저에게 DM 보내기. 이 이상은 못하겠음
    @MessageMapping("/dm/{user}")
    public void dm(Message message, @DestinationVariable String user) {
        System.out.println("DM 도착");
        System.out.println(user);
        String path = "/topic/dm/wns312";
        simpMessagingTemplate.convertAndSend(path, message); // 경로, 메시지 순서
    }
}
