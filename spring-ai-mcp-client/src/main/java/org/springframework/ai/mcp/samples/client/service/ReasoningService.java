package org.springframework.ai.mcp.samples.client.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.ai.mcp.samples.client.entity.ReasoningEntity;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;

@Service
public class ReasoningService {
    private final OpenAiChatModel openAiChatModel;

    public ReasoningService(OpenAiChatModel openAiChatModel) {
        this.openAiChatModel = openAiChatModel;
    }

    public Flux<String> solveMathProblem(String message) {
        Flux<String> content = ChatClient.builder(openAiChatModel).build().prompt().user(message).stream()
                .content();
        return content;
    }

    public List<ReasoningEntity> getReasoningEntityEntities(String message) {
        var converter = new BeanOutputConverter<>(new ParameterizedTypeReference<List<ReasoningEntity>>() {
        });
        Flux<String> flux = ChatClient.builder(openAiChatModel).build().prompt()
                .user(u -> u.text(message + "{format}").param("format", converter.getFormat())).stream()
                .content();
        String content = flux.collectList().block().stream().collect(Collectors.joining());
        List<ReasoningEntity> reasoningEntityEntities = converter.convert(content);
        return reasoningEntityEntities;
    }
}
