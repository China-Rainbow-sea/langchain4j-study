package com.rainbowsea.langchain4jchatfunctioncalling.controller;

import cn.hutool.core.date.DateUtil;
import com.rainbowsea.langchain4jchatfunctioncalling.service.FunctionAssistant;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 */
@RestController
@Slf4j
public class ChatFunctionCallingController
{
    @Resource
    private FunctionAssistant functionAssistant;

    //  http://localhost:9010/chatfunction/test1
    @GetMapping(value = "/chatfunction/test1")
    public String test1()
    {
        String chat = functionAssistant.chat("开张发票,公司：xxx科技有限公司 税号：xxx533 金额：668.12");

        System.out.println(chat);

        return "success : "+ DateUtil.now() + "\t"+chat;
    }
}
