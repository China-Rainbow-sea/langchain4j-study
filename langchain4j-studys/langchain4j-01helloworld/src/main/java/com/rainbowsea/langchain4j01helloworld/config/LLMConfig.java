package com.rainbowsea.langchain4j01helloworld.config;

//import dev.langchain4j.model.chat.ChatLanguageModel;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LLMConfig {


    // DeepSeek
    //@Bean
    @Bean(name = "deepseek")
    public ChatModel chatModelDeepSeek() {
        //String apiKey = System.getenv("deepseek_api");
        return OpenAiChatModel.builder()
                .apiKey(System.getenv("deepseek_api"))
                .modelName("deepseek-chat")
                .baseUrl("https://api.deepseek.com/v1")
                .build();
    }

    // aliQwen_api
    //@Bean
    @Bean(name = "qwen")
    public ChatModel chatModelQwen() {
        //String apiKey = System.getenv("aliQwen_api");
        return OpenAiChatModel.builder()
                .apiKey(System.getenv("aliQwen_api"))
                .modelName("qwen-plus")
                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                .build();
    }


}