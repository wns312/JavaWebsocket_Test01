package jyk.websocket.socket;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SocketController {

    @PostMapping("/api/v1/socket")
    public String test01(@RequestBody SocketRequest request) {
        System.out.println(request);
        return "";
    }

    @Data
    static class SocketRequest {
        private String name;
    }
}
