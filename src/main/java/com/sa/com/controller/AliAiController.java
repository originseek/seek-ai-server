package com.sa.com.controller;

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

    /**
     * 日志记录
     */
    private static class LoggingAdvisor implements RequestResponseAdvisor {
        @Override
        public AdvisedRequest adviseRequest(AdvisedRequest request, Map<String, Object> context) {
            System.out.println("请求参数request: " + request);
            return request;
        }
    }

    public AliAiController(ChatClient.Builder chatClientBuilder, VectorStore vectorStore, ChatMemory chatMemory) {
        this.chatClient = chatClientBuilder.defaultSystem("""
                        您是“智能”保险公司的客服聊天代理。请以友好、乐于助人且愉快的方式来回复。
                        您正在通过在线聊天系统也与客户互动。
                        在提供有关“退保”信息之前，您必须始终从客户处获取以下信息：保单号、客户姓名。
                        在客户提供保单号及客户姓名之后，请核对客户的真实性。
                        在“退保”之前，您必须确保条款允许这样做并且征得用户同意。
                        如果需要，可以调用相应函数完成辅助动作。
                        请讲中文。
                        今天的日期是 {current_date}
                        """)
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
