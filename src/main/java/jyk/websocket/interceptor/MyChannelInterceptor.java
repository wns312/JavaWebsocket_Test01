package jyk.websocket.interceptor;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.util.LinkedMultiValueMap;

import java.security.Principal;

/*
*
* 현재 인터셉터가 동작은 하지만
* 실제로 DM을 보내는 등의 시도는 전부 실패했으므로,
* 연결하는 방식을 아는 용도로만 남겨두었다.
* 실제 사용되지는 않으므로 무시해도 좋다
*
*/
public class MyChannelInterceptor implements ChannelInterceptor {

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        // accessor로 헤더에 편리하게접근 가능
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        StompCommand command = accessor.getCommand(); // 스톰프에 명령어

        // 만약 메시지 타입이 Connect라면 login을 세팅?
        if (StompCommand.CONNECT.equals(command.getMessageType())) {
            // 아마 이 시점에 유저가 목록에 등록되고, 사용할 수 있는 것으로 보이지만 확인 필요
            System.out.println("accessor.getLogin() : "+accessor.getLogin());
            User user = (User)accessor.getUser();
            accessor.setUser(user);
            System.out.println(user.getName()); // wns312
        }
        return message;
    }


}