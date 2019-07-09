package com.github.martingaston.application.communications;

import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.assertj.core.api.Assertions.*;

@DisplayName("A Receiver class")
class ReceiverTest {
    @DisplayName("Can receive messages")
    @Test
    void canReceiveMessage() throws IOException {
        ByteArrayInputStream input = new ByteArrayInputStream("It was the best of times.\r\nIt was the worst of times.".getBytes());
        Receiver receiver = new Receiver(input);

        assertThat(receiver.receive()).isEqualTo("It was the best of times.");
        assertThat(receiver.receive()).isEqualTo("It was the worst of times.");

    }

}