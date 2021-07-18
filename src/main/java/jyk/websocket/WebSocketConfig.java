package jyk.websocket;

import jyk.websocket.interceptor.MyChannelInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        String[] addressList = {"http://localhost", "http://172.23.176.1", "http://192.168.0.3"}; // 허용할 주소 리스트
        registry.addEndpoint("/hand-shake") // 핸드쉐이크를 어느 경로로 할 지 지정해준다.
                .setAllowedOrigins(addressList) // 허용할 경로를 지정해준다
                .withSockJS(); // SockJS를 함께 사용한다는 의미
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic", "/queue"); // 이 경로로 오는 요청을 컨트롤러로 넘겨준다?
        // 클라이언트는 이 접두어를 붙여서 요청을 보내야 하고, 서버에서는 @MessageMapping에 이 접두어를 빼고 경로를 지정해 요청을 받음
        // 왜 이따위로 만듬? 없애도 되는지 나중에 확인해보자
        config.setApplicationDestinationPrefixes("/app");
    }

    // 인터셉터 설정 : 현재 사용하지 않기 때문에 이렇게 연결한다는 용도로만 기억하자
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new MyChannelInterceptor());
    }

}
