package org.example.publisher;

import org.springframework.stereotype.Component;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Component
public class MessageEventPublisher {
    private final Sinks.Many<String> sink;
    private final Flux<String> flux;

    public MessageEventPublisher() {
        this.sink = Sinks.many().multicast().onBackpressureBuffer();
        this.flux = this.sink.asFlux();
    }

    public void publishMessage(String message) {
        sink.tryEmitNext(message);
    }

    public Flux<String> getMessageEvents() {
        return flux;
    }
} 