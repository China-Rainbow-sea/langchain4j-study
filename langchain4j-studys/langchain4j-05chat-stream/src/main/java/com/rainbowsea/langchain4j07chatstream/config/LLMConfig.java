package com.rainbowsea.langchain4j07chatstream.config;

import com.rainbowsea.langchain4j07chatstream.service.ChatAssistant;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.List;

/**
 * @Description: 知识出处，https://docs.langchain4j.dev/tutorials/response-streaming
 */
@Configuration
public class LLMConfig
{


    /**
     * @Description: 流式对话接口 StreamingChatModel
     * @Auther: zzyybs@126.com
     */
    @Bean
    public StreamingChatModel streamingChatModel(){
        return OpenAiStreamingChatModel.builder()
                .apiKey(System.getenv("aliQwen_api"))
                .modelName("qwen-plus")
                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                .build();
    }

    @Bean
    public ChatAssistant chatAssistant(StreamingChatModel streamingChatModel){
        return AiServices.create(ChatAssistant.class, streamingChatModel);
    }


    /**
    * @Description: 普通对话接口 ChatModel
    * @Auther: zzyybs@126.com
    */
    @Bean(name = "qwen")
    public ChatModel chatModelQwen()
    {
        return OpenAiChatModel.builder()
                .apiKey(System.getenv("aliQwen-api"))
                .modelName("qwen-plus")
                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                .build();
    }



}