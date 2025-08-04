package com.rainbowsea.langchain4jbootintegration.config;

import com.rainbowsea.langchain4jbootintegration.service.ChatAssistantQwen;
import com.rainbowsea.langchain4jbootintegration.service.ChatAssistantDeepSeek;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 知识出处 https://docs.langchain4j.dev/get-started
 */
@Configuration
public class LLMConfig {

    @Bean(name = "qwen")
    public ChatModel chatModelQwen() {
        return OpenAiChatModel.builder()
                .apiKey(System.getenv("aliQwen_api"))
                .modelName("qwen-plus")
                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                .build();

    }

    // 你使用第2种类，高阶API    AiService
    @Bean(name = "qwenAssistant")
    public ChatAssistantQwen chatAssistantQwen(@Qualifier("qwen") ChatModel chatModelQwen) {
        return AiServices.create(ChatAssistantQwen.class, chatModelQwen);
    }


    /**
     * @Description: 知识出处，https://api-docs.deepseek.com/zh-cn/
     */
    @Bean(name = "deepseek")
    public ChatModel chatModelDeepSeek() {
        return
                OpenAiChatModel.builder()
                        .apiKey(System.getenv("deepseek_api"))
                        .modelName("deepseek-chat")
                        //.modelName("deepseek-reasoner")
                        .baseUrl("https://api.deepseek.com/v1")
                        .build();
    }


    @Bean(name = "deepseekAssistant")
    public ChatAssistantDeepSeek chatAssistantDeepSeek(@Qualifier("deepseek") ChatModel chatModelDeepSeek) {
        return AiServices.create(ChatAssistantDeepSeek.class, chatModelDeepSeek);
    }
}
