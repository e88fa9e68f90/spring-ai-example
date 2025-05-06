package org.springframework.ai.mcp.samples.client.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;

@Service
public class CustomerSupportAssistant {
    private final ChatClient chatClient;

    public CustomerSupportAssistant(ChatClient.Builder builder, ChatMemory chatMemory) {

        this.chatClient = builder.defaultAdvisors(new MessageChatMemoryAdvisor(chatMemory), // CHAT MEMORY
                new SimpleLoggerAdvisor()).build();
    }

    public Flux<String> chat(String chatId, String userMessageContent) {

        return this.chatClient.prompt().user(userMessageContent)
                .advisors(advisor -> advisor.param("chat_memory_conversation_id", "678")
                        .param("chat_memory_response_size", 100))
                .stream().content();
    }

}
