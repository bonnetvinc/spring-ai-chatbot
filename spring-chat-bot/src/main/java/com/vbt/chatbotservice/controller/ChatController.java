package com.vbt.chatbotservice.controller;

import com.vbt.chatbotservice.dto.MessageDto;
import com.vbt.chatbotservice.langchain4j.LangChain4jAssistant;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    private final LangChain4jAssistant langChain4JAssistant;

    ChatController(LangChain4jAssistant langChain4JAssistant){
        this.langChain4JAssistant = langChain4JAssistant;
    }

    @PostMapping("/chat")
    @CrossOrigin(origins = "http://localhost:5173")
    public String chat(@RequestBody MessageDto userMessage) {
        return callSyncLangChain4j(userMessage.getChatId(), userMessage.getMessage());
    }

    private String callSyncLangChain4j(String chatId, String userMessage) {

        return langChain4JAssistant.chatSync(chatId, userMessage);

    }


}
