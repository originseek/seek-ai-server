/*
package com.sa.com.controller;

import com.sa.com.constants.PromptConst;
import org.springframework.ai.chat.client.AdvisedRequest;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.RequestResponseAdvisor;
import org.springframework.ai.chat.client.advisor.PromptChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.util.Map;

import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_RETRIEVE_SIZE_KEY;

@RestController
@CrossOrigin
@RequestMapping("/ali")
public class AliAiController {
    private final ChatClient chatClient;

    //日志记录
    private static class LoggingAdvisor implements RequestResponseAdvisor {
        @Override
        public AdvisedRequest adviseRequest(AdvisedRequest request, Map<String, Object> context) {
            System.out.println("请求参数request: " + request);
            return request;
        }
    }

    public AliAiController(ChatClient.Builder chatClientBuilder, VectorStore vectorStore, ChatMemory chatMemory) {
        this.chatClient = chatClientBuilder.defaultSystem(PromptConst.DEFAULT_PROMPT)
                .defaultAdvisors(new PromptChatMemoryAdvisor(chatMemory),
                        new QuestionAnswerAdvisor(vectorStore, SearchRequest.defaults()),
                        new LoggingAdvisor())
                .defaultFunctions("findPolicy", "cancelPolicy")
                .build();
    }

    @CrossOrigin
    @GetMapping(value = "ai/streamAsString", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamAsString(@RequestParam(value = "message", defaultValue = "哈喽") String message) {
        Flux<String> content = chatClient.prompt()
                .system(s -> s.param("current_date", LocalDate.now().toString()))
                //指定用户ID
                //.advisors(a -> a.param(CHAT_MEMORY_CONVERSATION_ID_KEY, chatId).param(CHAT_MEMORY_RETRIEVE_SIZE_KEY, 100))
                .advisors(a -> a.param(CHAT_MEMORY_RETRIEVE_SIZE_KEY, 100))
                .user(message)
                .stream()
                .content();
        return content.concatWith(Flux.just("[complete]"));
    }
}
*/
