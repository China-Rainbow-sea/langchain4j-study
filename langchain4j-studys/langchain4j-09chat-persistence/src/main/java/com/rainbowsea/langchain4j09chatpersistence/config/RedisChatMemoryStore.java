package com.rainbowsea.langchain4j09chatpersistence.config;

import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.ChatMessageDeserializer;
import dev.langchain4j.data.message.ChatMessageSerializer;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description: https://docs.langchain4j.dev/tutorials/chat-memory#persistence
 */
@Component
public class RedisChatMemoryStore implements ChatMemoryStore
{

    // 存储到 Redis 的 key的一个标识前缀
    public static final String CHAT_MEMORY_PREFIX = "CHAT_MEMORY:";

    @Resource
    private RedisTemplate<String,String> redisTemplate;



    @Override
    public List<ChatMessage> getMessages(Object memoryId)
    {
        // 从 Redis 当中获取数据
        String retValue = redisTemplate.opsForValue().get(CHAT_MEMORY_PREFIX + memoryId);

        return  ChatMessageDeserializer.messagesFromJson(retValue);
    }

    @Override
    public void updateMessages(Object memoryId, List<ChatMessage> messages)
    {
        // 添加数据到 Redis 当中
        redisTemplate.opsForValue()
                .set(CHAT_MEMORY_PREFIX + memoryId, ChatMessageSerializer.messagesToJson(messages));
    }

    @Override
    public void deleteMessages(Object memoryId)
    {
        // 删除 Redis 数据
        redisTemplate.delete(CHAT_MEMORY_PREFIX + memoryId);
    }
}
