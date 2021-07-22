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
        // 이 경로로 오는 요청은 컨트롤러를 거치지 않고 보낼 수 있다. SimpleBroker로 등록했기 때문이다.
        config.enableSimpleBroker("/topic", "/queue"); // 이 경로로 오는 요청을 SimpleBroker로 등록한다
        // 이 경로로 오는 요청은 컨트롤러를 거치게 된다.
        config.setApplicationDestinationPrefixes("/app");
    }

    // 인터셉터 설정 : 현재 사용하지 않기 때문에 이렇게 연결한다는 용도로만 기억하자
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new MyChannelInterceptor());
    }

}
