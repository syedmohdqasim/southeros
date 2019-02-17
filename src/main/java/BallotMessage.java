class BallotMessage {
    private Kingdom sender;
    private Kingdom receiver;
    private String Message;


    BallotMessage(Kingdom sender, Kingdom receiver, String message) {
        this.sender = sender;
        this.receiver = receiver;
        Message = message;
    }

    Kingdom getSender() {
        return sender;
    }

    Kingdom getReceiver() {
        return receiver;
    }

    String getMessage() {
        return Message;
    }
}
