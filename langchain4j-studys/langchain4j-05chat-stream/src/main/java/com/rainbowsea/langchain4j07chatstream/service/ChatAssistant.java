package com.rainbowsea.langchain4j07chatstream.service;

import reactor.core.publisher.Flux;

/**
 * @Date 2025-05-30 16:19
 * @Description: TODO
 */
public interface ChatAssistant
{
    /**
     * 普通输出，不是流式的
     * @param prompt
     * @return
     */
    String chat(String prompt);

    /**
     * 流式输出，是流式的
     * 注意：流式输出返回的要为 Flux<T> 类型的数据类型
     * @param prompt
     * @return
     */
    Flux<String> chatFlux(String prompt);
}
