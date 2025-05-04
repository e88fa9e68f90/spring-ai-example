package org.springframework.ai.mcp.samples.client.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.ai.mcp.samples.client.entity.ActorFilms;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.template.st.StTemplateRenderer;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/my")
public class MyController {
    private final ChatClient chatClient;
    private final OpenAiChatModel chatModel;

    public MyController(ChatClient.Builder chatClientBuilder, OpenAiChatModel openAiChatModel) {
        this.chatClient = chatClientBuilder.build();
        this.chatModel = openAiChatModel;
    }

    /**
     * 一个简答的问答，输出string
     * 
     * @param userInput
     * @return
     */
    @GetMapping("/v1/ai")
    public String generation(String userInput) {
        return this.chatClient.prompt().user(userInput).call().content();
    }

    /**
     * 您可以使用该方法轻松地将 AI 模型的输出映射到此记录entity()，如下所示：
     * 
     * @param userInput
     * @return
     */
    @GetMapping("/v2/ai")
    public ActorFilms actorFilms(String userInput) {
        return this.chatClient.prompt().user(userInput).call().entity(ActorFilms.class);
    }

    /**
     * 还有一种entity带有签名的重载方法entity(ParameterizedTypeReference<T>
     * type)，可让您指定诸如通用列表之类的类型：
     * 
     * @param userInput
     * @return
     */
    @GetMapping("/v3/ai")
    public List<ActorFilms> actorFilmsList(String userInput) {
        List<ActorFilms> actorFilms = chatClient.prompt().user(userInput).call()
                .entity(new ParameterizedTypeReference<List<ActorFilms>>() {
                });
        return actorFilms;
    }

    /**
     * 流式响应
     * 
     * @param userInput
     * @return
     */
    @GetMapping("/v4/ai")
    public Flux<String> actorFilmsStream(String userInput) {
        Flux<String> flux = chatClient.prompt().user(userInput).stream().content();
        return flux;
    }

    @GetMapping("/v5/ai")
    public List<ActorFilms> actorFilmsStreamv2(String userInput) {
        var converter = new BeanOutputConverter<>(new ParameterizedTypeReference<List<ActorFilms>>() {
        });
        Flux<String> flux = this.chatClient.prompt()
                .user(u -> u.text(userInput + "{format}").param("format", converter.getFormat())).stream().content();
        String content = flux.collectList().block().stream().collect(Collectors.joining());

        List<ActorFilms> actorFilms = converter.convert(content);
        return actorFilms;
    }

    @GetMapping("/v6/ai")
    public String actorFilmsStreamv3(String userInput) {
        String answer = ChatClient.create(chatModel).prompt()
                .user(u -> u.text("Tell me the names of 5 movies whose soundtrack was composed by {composer}")
                        .param("composer", "John Williams"))
                .call().content();
        return answer;
    }

    @GetMapping("/v7/ai")
    public String actorFilmsStreamv4(String userInput, String composer) {
        String answer = ChatClient.create(chatModel).prompt()
                .user(u -> u.text(userInput + " {composer}").text(userInput).param("composer", "John Williams").param(
                        "composer", composer))
                .templateRenderer(StTemplateRenderer.builder().startDelimiterToken('<').endDelimiterToken('>').build())
                .call().content();
        return answer;
    }
}
