/*
package com.sa.com.controller;

import com.sa.com.constants.PromptConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_RETRIEVE_SIZE_KEY;

@RestController
@RequestMapping(value = "/ds")
@CrossOrigin
@Slf4j
public class DeepSeekAiController {
    //基于 ollama 部署的本地模型
    private final ChatClient chatClient;

    public DeepSeekAiController(ChatModel chatModel) {
        this.chatClient = ChatClient.builder(chatModel)
                .defaultAdvisors(new MessageChatMemoryAdvisor(new InMemoryChatMemory()))
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .defaultOptions(OllamaOptions.builder().build()).build();
    }
    @CrossOrigin
    @GetMapping(value = "ai/streamAsString", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamAsString(@RequestParam(value = "message", defaultValue = "哈喽") String message) {
        Flux<String> content = chatClient.prompt(PromptConst.DEFAULT_PROMPT)
                .system(s -> s.param("current_date", LocalDate.now().toString()))
                .advisors(a -> a.param(CHAT_MEMORY_RETRIEVE_SIZE_KEY, 100))
                .user(message)
                .stream().content();
        log.info("基于ollama部署的本地模型执行content:{}", content);
        return content.concatWith(Flux.just("[complete]"));
    }
}
*/
