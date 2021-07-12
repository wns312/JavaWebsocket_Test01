package jyk.websocket.interceptor;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;

import java.security.Principal;

public class MyChannelInterceptor implements ChannelInterceptor {
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        StompCommand command = accessor.getCommand();

        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            accessor.setUser(new User(accessor.getLogin()));
        }

        // channel.send(); 메시지 채널에는 이 메시지만 있음
//        System.out.println("message.getHeaders() : "+message.getHeaders());
//        System.out.println("message.getHeaders().get(\"simpMessageType\") : "+message.getHeaders().get("simpMessageType"));
//        System.out.println("message.getHeaders().get(\"stompCommand\") : "+message.getHeaders().get("stompCommand"));
//        System.out.println("message.getHeaders().get(\"nativeHeaders\") : "+message.getHeaders().get("nativeHeaders"));
//        System.out.println("message.getHeaders().get(\"destination\") : "+message.getHeaders().get("destination"));
//        System.out.println("message.getHeaders().get(\"simpSessionAttributes\") : "+message.getHeaders().get("simpSessionAttributes"));
//        System.out.println("message.getHeaders().get(\"simpHeartbeat\") : "+message.getHeaders().get("simpHeartbeat"));
//        System.out.println("message.getHeaders().get(\"simpSubscriptionId\") : "+message.getHeaders().get("simpSubscriptionId"));
//        System.out.println("message.getHeaders().get(\"simpSessionId\") : "+message.getHeaders().get("simpSessionId"));
//        System.out.println("message.getHeaders().get(\"simpDestination\") : "+message.getHeaders().get("simpDestination"));
//
//        System.out.println("message.getPayload() : "+message.getPayload());
//        System.out.println("command.name() : "+command.name());
//        System.out.println("command.getMessageType() : "+command.getMessageType());
//        System.out.println("accessor.getLogin() : "+accessor.getLogin());
//        System.out.println("accessor.getCommand() : "+accessor.getCommand());
//        System.out.println("accessor.getAcceptVersion() : "+accessor.getAcceptVersion());
//        System.out.println("accessor.getPasscode() : "+accessor.getPasscode());

//        System.out.println("accessor.getAck() : "+accessor.getAck());
//        System.out.println("accessor.getHost() : "+accessor.getHost());
//        System.out.println("accessor.getMessage() : "+accessor.getMessage());
//        System.out.println("accessor.getMessageId() : "+accessor.getMessageId());
//        System.out.println("accessor.getNack() : "+accessor.getNack());
//        System.out.println("accessor.getReceipt() : "+accessor.getReceipt());
//        System.out.println("accessor.getReceiptId() : "+accessor.getReceiptId());
        return message;
    }
}