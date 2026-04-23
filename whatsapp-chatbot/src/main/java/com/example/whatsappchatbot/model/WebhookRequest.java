package com.example.whatsappchatbot.model;

import jakarta.validation.constraints.NotBlank;

/**
 * Minimal JSON payload to simulate a WhatsApp inbound message.
 *
 * Example:
 * {
 *   "from": "233000000000",
 *   "text": "Hi"
 * }
 */
public record WebhookRequest(
        @NotBlank(message = "`from` is required") String from,
        @NotBlank(message = "`text` is required") String text
) {}

