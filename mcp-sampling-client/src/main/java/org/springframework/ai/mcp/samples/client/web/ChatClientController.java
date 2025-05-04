package org.springframework.ai.mcp.samples.client.web;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.mcp.samples.client.service.CustomerSupportAssistant;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/chat-client")
public class ChatClientController {
    @Autowired
    CustomerSupportAssistant customerSupportAssistant;
    private final OpenAiChatModel chatModel;

    public ChatClientController(ChatClient.Builder builder, OpenAiChatModel openAiChatModel) {
        chatModel = openAiChatModel;
    }

    @GetMapping("/chat.json")
    public Flux<String> chat(String chatId, String userMessageContent) {
        return this.customerSupportAssistant.chat(chatId, userMessageContent);
    }

}
