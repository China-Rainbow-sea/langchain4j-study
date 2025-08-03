package com.rainbowsea.langchain4j04chatimage.controller;

import dev.langchain4j.data.message.ImageContent;
import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Base64;

/**
 *
 */
@RestController
@Slf4j
public class ImageModelController {
    @Autowired
    private ChatModel chatModel;


    @Value("classpath:static/images/mi.jpg")
    // 图片也是一种资源，也可以用 @Value 进行赋值注入
    // classpath表示 resources 根目录
    private Resource resource;    //import org.springframework.core.io.Resource;

    /**
     * @Description: 通过Base64编码将图片转化为字符串，结合ImageContent和TextContent
     * 一起发送到模型进行处理。
     * 测试地址：http://localhost:9004/image/call
     */
    @GetMapping(value = "/image/call")
    public String readImageContent() throws IOException {

        // 注意：这里我们的计算机还是大模型是无法直接识别，传输图片的
        // 我们需要将图片转换为 byte[] 二进制比特数据才能传输，才能识别
        byte[] byteArray = resource.getContentAsByteArray();
        String base64Data = Base64.getEncoder().encodeToString(byteArray);

        // 将发送给大模型的信息，封装到 UserMessage 对象当中
        UserMessage userMessage = UserMessage.from(
                TextContent.from("从以下图片中获取来源网站名称，股价走势和5月30号股价"),
                // mimeType 指明让大模型解读的文件类型是::image/jpg ，让大模型更容易解读
                ImageContent.from(base64Data, "image/jpg")
        );

        ChatResponse chatResponse = chatModel.chat(userMessage);
        String result = chatResponse.aiMessage().text();

        System.out.println(result);

        return result;
    }
}
