package org.example.publisher;

import org.example.model.User;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Component
public class UserEventPublisher {
    private final Sinks.Many<User> sink;
    private final Flux<User> flux;

    public UserEventPublisher() {
        this.sink = Sinks.many().multicast().onBackpressureBuffer();
        this.flux = this.sink.asFlux();
    }

    public void publishUserCreated(User user) {
        sink.tryEmitNext(user);
    }

    public Flux<User> getUserEvents() {
        return flux;
    }
} 