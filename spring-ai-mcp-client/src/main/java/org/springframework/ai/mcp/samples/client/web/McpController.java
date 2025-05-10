package org.springframework.ai.mcp.samples.client.web;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.modelcontextprotocol.client.McpSyncClient;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/mcp/client")
public class McpController {
    private static final Logger LOGGER = LoggerFactory.getLogger(McpController.class);
    private static final String JWT = "eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJkNTIzODYzZC0zODg4LTQzNmQtYjJjZC0zM2Y1MzI5YTBlYmYiLCJ1c2VySWQiOiIxNzUyOTIwODEzNTMzNzYxNTM3IiwibmFtZSI6IuWtmemAmiIsInZlcnNpb24iOjAsInVzZXJUeXBlIjoxLCJleHBpcmUiOjc3NzYwMDAsInBsYXlsb2FkIjoie1wiY3VzdG9tVHlwZVwiOlwiMVwiLFwiYWNjb3VudE5hbWVcIjpcImY0NjI4NTRiLTgyMGQtNDljNy1hOGQyLWNjYjBhMzNiZWFlNlwiLFwidXNlck5hbWVcIjpcImY0NjI4NTRiLTgyMGQtNDljNy1hOGQyLWNjYjBhMzNiZWFlNlwiLFwidG9rZW5cIjpcImY0NjI4NTRiLTgyMGQtNDljNy1hOGQyLWNjYjBhMzNiZWFlNlwifSIsImV4cCI6MTc1MzU0NjcwOH0.jW16SKshZMmZ668G8OQVV2vPRkq6oEIW6-klwLfYrCmJdi3NZCJpHmM0L1bKcxJ8oqEWt8ckacW8m7sRlLtix7d-lb62PAW9bu0SeuOo3ZE_IdsFNLwDDp9NIh5kY71f3B6146TvswlP59OlgYV9HJU9bWn5ZJmxftmxKdQ_iVI";
    private ChatClient chatClient;
    @Autowired
    private ChatMemory chatMemory;
    @Autowired
    private OpenAiChatModel openAiChatModel;
    @Autowired
    private List<McpSyncClient> mcpSyncClients;

    @Autowired
    public McpController(OpenAiChatModel openAiChatModel, ChatMemory chatMemory, List<McpSyncClient> mcpSyncClients) {
        var mcpToolProvider = new SyncMcpToolCallbackProvider(mcpSyncClients);
        chatClient = ChatClient.builder(openAiChatModel).defaultAdvisors(new MessageChatMemoryAdvisor(chatMemory))
                .defaultToolCallbacks(mcpToolProvider)
                .build();
    }
    @GetMapping("/message-chat")
    public String chat(String message) {
        var mcpToolProvider = new SyncMcpToolCallbackProvider(mcpSyncClients);
        ChatClient chatClient = ChatClient.builder(openAiChatModel).defaultToolCallbacks(mcpToolProvider).build();
        return chatClient.prompt().user(message + " jwt=" + JWT).call().content();
    }

    @GetMapping("/ai/generate")
    public Map generate(@RequestParam(value = "message", defaultValue = "给我讲故事") String message) {
        return Map.of("generation", this.openAiChatModel.call(message));
    }

    @GetMapping(value = "/ai/generateStream")
    public Flux<String> generateStreamMemory(
            @RequestParam(value = "message", defaultValue = "给我做个自我介绍") String message) {
        var mcpToolProvider = new SyncMcpToolCallbackProvider(mcpSyncClients);
        Prompt prompt = new Prompt(new UserMessage(message + " jwt=" + JWT));
        ChatClient chatClient = ChatClient.builder(openAiChatModel).defaultToolCallbacks(mcpToolProvider).build();
        return chatClient.prompt(prompt).system("你是一个保险行业的专家").stream()
                .content();
    }

    @GetMapping(value = "/ai/generateStream-memory")
    public Flux<String> generateStream(@RequestParam(value = "message", defaultValue = "给我做个自我介绍") String message) {
        var mcpToolProvider = new SyncMcpToolCallbackProvider(mcpSyncClients);
        Prompt prompt = new Prompt(new UserMessage(message + " jwt=" + JWT));
        ChatClient chatClient = ChatClient.builder(openAiChatModel).defaultToolCallbacks(mcpToolProvider).build();
        return chatClient.prompt(prompt).system("你是一个保险行业的专家").advisors(
                advisor -> advisor.param("chat_memory_conversation_id", "678").param("chat_memory_response_size", 100))
                .stream().content();
    }

    @GetMapping(value = "/ai/generateStream-memory1")
    public Flux<String> generateStream1(@RequestParam(value = "message", defaultValue = "给我做个自我介绍") String message) {
        LOGGER.info("进入人工智能问答区域：{}", message);
        var mcpToolProvider = new SyncMcpToolCallbackProvider(mcpSyncClients);
        // 打印需要注册的mcp server服务
        for (ToolCallback toolCallback : mcpToolProvider.getToolCallbacks()) {
            LOGGER.info("mcp server getToolDefinition: {}, {}", toolCallback.getToolDefinition().name(),
                    toolCallback.getToolDefinition().description());
            LOGGER.info("mcp server getToolMetadata: {}", toolCallback.getToolMetadata());
        }
        return ChatClient.builder(openAiChatModel).defaultAdvisors(new MessageChatMemoryAdvisor(chatMemory))
                .defaultToolCallbacks(mcpToolProvider)
                .build().prompt()
                .system("你是一个保险行业的专家。")
                .advisors(
                        advisor -> advisor.param("chat_memory_conversation_id", "678")
                                .param("chat_memory_response_size", 100))
                .user(message + " jwt=" + JWT).stream().content();
    }

    @GetMapping(value = "/ai/generateStream-memory2")
    public Flux<String> generateStream2(@RequestParam("id") String id,
            @RequestParam(value = "message", defaultValue = "给我做个自我介绍") String message) {
        LOGGER.info("进入人工智能问答区域：{}", message);
        return this.chatClient.prompt().system("你是一个保险行业的专家，禁止回答保险以外的问题。")
                .advisors(advisor -> advisor.param("chat_memory_conversation_id", id)
                        .param("chat_memory_response_size", 100))
                .user(message + " jwt=" + JWT).stream().content();
    }
    @GetMapping("/ai/generateStream-v2")
    public Flux<ChatResponse> generateStreamChatClientResponse(
            @RequestParam(value = "message", defaultValue = "给我做个自我介绍") String message) {
        var mcpToolProvider = new SyncMcpToolCallbackProvider(mcpSyncClients);
        Prompt prompt = new Prompt(new UserMessage(message + " jwt=" + JWT));
        ChatClient chatClient = ChatClient.builder(openAiChatModel).defaultToolCallbacks(mcpToolProvider).build();
        return chatClient.prompt(prompt).stream().chatResponse();
    }

    @GetMapping("/ai/generate111")
    public Map asdasdsad(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        return Map.of("generation", this.openAiChatModel.call(message));
    }

    @GetMapping("/ai/generateStream111")
    public Flux<ChatResponse> generateStream111(
            @RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        Prompt prompt = new Prompt(new UserMessage(message));
        return this.openAiChatModel.stream(prompt);
    }
    @GetMapping(value = "/words", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamWords(@RequestParam String sentence) {
        return null;
    }

}
