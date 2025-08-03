package com.rainbowsea.langchain4j06chatmemory.controller;

import cn.hutool.core.date.DateUtil;
import com.rainbowsea.langchain4j06chatmemory.service.ChatMemoryAssistant;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.response.ChatResponse;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * @auther zzyybs@126.com
 * @Date 2025-05-30 19:16
 * @Description: TODO
 */
@RestController
@Slf4j
public class ChatMemoryController
{

    @Resource(name = "chatMessageWindowChatMemory")
    private ChatMemoryAssistant chatMessageWindowChatMemory;

    /**
     * @Description: MessageWindowChatMemory实现聊天功能
     */
    @GetMapping(value = "/chatmemory/test2")
    public String chatMessageWindowChatMemory()
    {
        chatMessageWindowChatMemory.chatWithChatMemory(1L, "你好！我的名字是Java.");
        String answer01 = chatMessageWindowChatMemory.chatWithChatMemory(1L, "我的名字是什么");
        System.out.println("answer01返回结果："+answer01);

        chatMessageWindowChatMemory.chatWithChatMemory(3L, "你好！我的名字是C++");
        String answer02 = chatMessageWindowChatMemory.chatWithChatMemory(3L, "我的名字是什么");
        System.out.println("answer02返回结果："+answer02);

        return "chatMessageWindowChatMemory success : "
                + DateUtil.now()+"<br> \n\n answer01: "+answer01+"<br> \n\n answer02: "+answer02;

    }








    @Resource(name = "chatTokenWindowChatMemory")
    private ChatMemoryAssistant chatTokenWindowChatMemory;



    /**
     * @Description: TokenWindowChatMemory实现聊天功能
     * @Auther: zzyybs@126.com
     */
    @GetMapping(value = "/chatmemory/test3")
    public String chatTokenWindowChatMemory()
    {
        chatTokenWindowChatMemory.chatWithChatMemory(1L, "你好！我的名字是mysql");
        String answer01 = chatTokenWindowChatMemory.chatWithChatMemory(1L, "我的名字是什么");
        System.out.println("answer01返回结果："+answer01);

        chatTokenWindowChatMemory.chatWithChatMemory(3L, "你好！我的名字是oracle");
        String answer02 = chatTokenWindowChatMemory.chatWithChatMemory(3L, "我的名字是什么");
        System.out.println("answer02返回结果："+answer02);

        return "chatTokenWindowChatMemory success : "
                + DateUtil.now()+"<br> \n\n answer01: "+answer01+"<br> \n\n answer02: "+answer02;
    }
}
