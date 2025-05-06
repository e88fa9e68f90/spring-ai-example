package org.springframework.ai.mcp.samples.client.web;

import java.util.List;

import org.springframework.ai.mcp.samples.client.entity.ReasoningEntity;
import org.springframework.ai.mcp.samples.client.service.ReasoningService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/reasoning")
public class ReasoningController {
    private final ReasoningService reasoningService;

    public ReasoningController(ReasoningService reasoningService) {
        this.reasoningService = reasoningService;
    }

    @GetMapping("/solve")
    public Flux<String> solve(String message) {
        return reasoningService.solveMathProblem(message);
    }

    @GetMapping("/solve1")
    public List<ReasoningEntity> solve1(String message) {
        return reasoningService.getReasoningEntityEntities(message);
    }
}
