package com.rainbowsea.langchain4jbootintegration.controller;

import com.rainbowsea.langchain4jbootintegration.service.ChatAssistantDeepSeek;
import com.rainbowsea.langchain4jbootintegration.service.ChatAssistantQwen;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: https://docs.langchain4j.dev/tutorials/spring-boot-integration
 */
@RestController
@Slf4j
public class DeclarativeAIServiceController
{
    @Resource(name = "qwenAssistant")
    private ChatAssistantQwen chatAssistantQwen;

    // http://localhost:9008/chatapi/highapi
    @GetMapping(value = "/chatapi/highapi")
    public String highApi(@RequestParam(value = "prompt", defaultValue = "你是谁") String prompt)
    {
        return chatAssistantQwen.chat(prompt);
    }



    @Resource(name = "deepseekAssistant")
    private ChatAssistantDeepSeek chatAssistantDeepSeek;



    // http://localhost:9008/chatapi/highapi02
    @GetMapping(value = "/chatapi/highapi02")
    public String highApi02(@RequestParam(value = "prompt", defaultValue = "你是谁") String prompt)
    {
        return chatAssistantDeepSeek.chat(prompt);
    }
}
