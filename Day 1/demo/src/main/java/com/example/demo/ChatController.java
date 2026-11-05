package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ChatController {
    @Autowired
    private ChatService chatService;

    @PostMapping("/chat")
    public String chat(@RequestBody String message){
        return chatService.chat(message);
    }
}
