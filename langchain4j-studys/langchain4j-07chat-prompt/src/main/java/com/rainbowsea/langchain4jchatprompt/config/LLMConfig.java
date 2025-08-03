package com.rainbowsea.langchain4jchatprompt.config;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.rainbowsea.langchain4jchatprompt.service.LawAssistant;
/**
 */
@Configuration
public class LLMConfig
{
    @Bean
    public ChatModel chatModel()
    {
        return OpenAiChatModel.builder()
                .apiKey(System.getenv("aliQwen_api"))  // 根据自身系统变量当中配置的变量名
                .modelName("qwen-long")
                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                .build();
    }

    @Bean
    public LawAssistant lawAssistant(ChatModel chatModel) {
        return AiServices.create(LawAssistant.class, chatModel);
    }
}
