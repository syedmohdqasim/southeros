package com.codingchallenge;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BallotMessageTest {

    BallotMessage ballotMessage;
    Kingdom sender;
    Kingdom receiver;
    String message;

    @Before
    public void setUp() throws Exception {
        sender = new Kingdom("kingdom1", "emblem1");
        receiver = new Kingdom("kingdom2", "emblem2");
        message = "message";
        ballotMessage = new BallotMessage(sender, receiver, message);
    }

    @Test
    public void getSenderShouldReturnSenderOfMessage() {
        assertEquals(sender, ballotMessage.getSender());
    }

    @Test
    public void getReceiverShouldReturnReceiverOfMessage() {
        assertEquals(receiver, ballotMessage.getReceiver());

    }

    @Test
    public void getMessageShouldReturnCorrectContentOfMessage() {
        assertEquals(message, ballotMessage.getMessage());
    }
}