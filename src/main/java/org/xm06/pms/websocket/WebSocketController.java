package org.xm06.pms.websocket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xm06.pms.model.InformModel;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@RestController
public class WebSocketController {
    @RequestMapping("/socket/push/{cid}")
    public Map pushMessage(@PathVariable("cid") String cid, String message) {
        Map<String, Object> result = new HashMap<>();
        try {
            WebSocketServer.sendMessage(new InformModel(), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
