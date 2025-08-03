package com.rainbowsea.langchain4j02lowhighapi.controller;

import com.rainbowsea.langchain4j02lowhighapi.service.ChatAssistant;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 */
@RestController
@Slf4j
public class HighApiController
{
    @Resource
    private ChatAssistant chatAssistant;

    @GetMapping(value = "/highapi/highapi")
    public String highApi(@RequestParam(value = "prompt", defaultValue = "你是谁") String prompt)
    {
        return chatAssistant.chat(prompt);
    }
}