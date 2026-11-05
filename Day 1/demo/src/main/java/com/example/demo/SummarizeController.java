package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SummarizeController {
    @Autowired
    private SummarizeService summarizeService;

    @PostMapping("/summarize")
    public String summarize(@RequestBody String ticket){
        return summarizeService.summarize(ticket);
    }
}
