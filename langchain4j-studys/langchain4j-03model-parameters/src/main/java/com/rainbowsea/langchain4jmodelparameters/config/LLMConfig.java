package com.rainbowsea.langchain4jmodelparameters.config;

import com.rainbowsea.langchain4jmodelparameters.listener.TestChatModelListener;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.List;

/**
 * @Date 2025-05-27 22:04
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
                .logRequests(true) // 日志界别设置为debug才有效
                .logResponses(true)// 日志界别设置为debug才有效
                .listeners(List.of(new TestChatModelListener()))  //监听器
                .maxRetries(2)// 重试机制共计2次
                .timeout(Duration.ofSeconds(2)) //向大模型发送请求时，如在指定时间内没有收到响应，该请求将被中断并报requesttimedout
                .build();
    }
}
