//Represents an Ballot containing messages

import java.util.ArrayList;
import java.util.List;

class Ballot {
    private List<BallotMessage> ballotMessages;

    Ballot() {
        this.ballotMessages = new ArrayList<>();
    }

    BallotMessage getBallotMessage(Integer messageNumber) {
        return ballotMessages.get(messageNumber);
    }

    void addBallotMessage(BallotMessage ballotMessage) {
        this.ballotMessages.add(ballotMessage);
    }
}
