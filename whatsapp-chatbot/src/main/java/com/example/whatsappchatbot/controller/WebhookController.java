package com.example.whatsappchatbot.controller;

import com.example.whatsappchatbot.model.WebhookRequest;
import com.example.whatsappchatbot.model.WebhookResponse;
import com.example.whatsappchatbot.service.ReplyService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
public class WebhookController {
    private static final Logger log = LoggerFactory.getLogger(WebhookController.class);

    private final ReplyService replyService;

    public WebhookController(ReplyService replyService) {
        this.replyService = replyService;
    }

    @GetMapping(path = "/", produces = MediaType.TEXT_PLAIN_VALUE)
    public String home() {
        return """
                WhatsApp Chatbot backend is running.

                Use POST /webhook with JSON:
                { "from": "233000000000", "text": "Hi" }
                """;
    }

    @PostMapping(path = "/webhook", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public WebhookResponse webhook(@Valid @RequestBody WebhookRequest request) {
        var receivedAt = Instant.now();
        var incomingText = request.text();

        log.info("Incoming message receivedAt={} from={} text={}", receivedAt, request.from(), incomingText);

        var reply = replyService.replyFor(incomingText);
        log.info("Outgoing reply receivedAt={} to={} reply={}", receivedAt, request.from(), reply);

        return new WebhookResponse(reply);
    }
}

