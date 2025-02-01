package org.example.controller;

import org.example.model.User;
import org.example.publisher.MessageEventPublisher;
import org.example.publisher.UserEventPublisher;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@Controller
@RequiredArgsConstructor
public class SubscriptionController {
    private final UserEventPublisher userEventPublisher;
    private final MessageEventPublisher messageEventPublisher;

    @SubscriptionMapping
    public Flux<User> userCreated() {
        return userEventPublisher.getUserEvents();
    }

    @SubscriptionMapping
    public Flux<String> messageReceived() {
        return messageEventPublisher.getMessageEvents();
    }

    // Метод для тестирования отправки сообщений
    public void sendMessage(String message) {
        messageEventPublisher.publishMessage(message);
    }
} 