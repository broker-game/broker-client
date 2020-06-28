package io.github.jabrena.broker;

import io.github.jabrena.broker.impl.ReaderImpl;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReaderBuilder {

    private final String broker;

    private String topic;

    public ReaderBuilder topic(String topic) {
        this.topic = topic;
        return this;
    }

    public Reader create() {

        return new ReaderImpl(broker, topic);
    }
}
