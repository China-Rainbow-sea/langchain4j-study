package com.rainbowsea.langchain4jbootintegration.service;

import dev.langchain4j.service.spring.AiService;

import static dev.langchain4j.service.spring.AiServiceWiringMode.EXPLICIT;

/**
 */
@AiService(wiringMode = EXPLICIT, chatModel = "qwen")
public interface ChatAssistantQwen
{
    String chat(String prompt);
}
