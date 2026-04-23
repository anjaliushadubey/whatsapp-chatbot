package com.example.whatsappchatbot.service;

import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Map;

@Service
public class ReplyService {
    private static final Map<String, String> REPLIES = Map.of(
            "hi", "Hello",
            "bye", "Goodbye"
    );

    public String replyFor(String incomingText) {
        if (incomingText == null) return defaultReply();
        var normalized = incomingText.trim().toLowerCase(Locale.ROOT);
        return REPLIES.getOrDefault(normalized, defaultReply());
    }

    private String defaultReply() {
        return "I can reply to: Hi, Bye";
    }
}

