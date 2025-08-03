package com.rainbowsea.langchain4j06chatmemory.config;

import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.memory.chat.TokenWindowChatMemory;
import dev.langchain4j.model.TokenCountEstimator;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiTokenCountEstimator;
import dev.langchain4j.service.AiServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.rainbowsea.langchain4j06chatmemory.service.ChatMemoryAssistant;

/**
 * @Description: 知识出处，https://docs.langchain4j.dev/tutorials/chat-memory/#eviction-policy
 */
@Configuration
public class LLMConfig
{
    @Bean
    public ChatModel chatModel()
    {
        return OpenAiChatModel.builder()
                    .apiKey(System.getenv("aliQwen_api"))  //你自己配置到系统变量当中的值
                    .modelName("qwen-long")
                    .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                .build();
    }



    /**
    * @Description: 按照MessageWindowChatMemory
     *知识出处，https://docs.langchain4j.dev/tutorials/chat-memory/#eviction-policy
    */
    @Bean(name = "chatMessageWindowChatMemory")
    public ChatMemoryAssistant chatMessageWindowChatMemory(ChatModel chatModel)
    {

        return AiServices.builder(ChatMemoryAssistant.class)
                .chatModel(chatModel)
                //按照memoryId对应创建了一个chatMemory
                .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(100))
                .build();
    }








    /**
    * @Description: 按照 TokenWindowChatMemory,
     * 知识出处，https://docs.langchain4j.dev/tutorials/chat-memory/#eviction-policy
    */
    @Bean(name = "chatTokenWindowChatMemory")
    public ChatMemoryAssistant chatTokenWindowChatMemory(ChatModel chatModel)
    {
        //1 TokenCountEstimator默认的token分词器，需要结合Tokenizer计算ChatMessage的token数量
        TokenCountEstimator openAiTokenCountEstimator = new OpenAiTokenCountEstimator("gpt-4");

        return AiServices.builder(ChatMemoryAssistant.class)
                .chatModel(chatModel)
                .chatMemoryProvider(memoryId -> TokenWindowChatMemory.withMaxTokens(1000,openAiTokenCountEstimator))
                .build();
    }
}
