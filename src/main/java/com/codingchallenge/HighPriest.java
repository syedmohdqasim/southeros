package com.codingchallenge;//Represents High Priest of com.codingchallenge.Southeros

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class HighPriest {

    private Random random;

    public HighPriest() {
        this.random = new Random();
    }

    List<BallotMessage> getBallotMessages(Ballot ballot) {
        List<BallotMessage> randomMessageList = new ArrayList<>();
        int numberOfMessagesToPick = 6;
        for (int i = 0; i < numberOfMessagesToPick; i++) {
            int randomNumber = random.nextInt(ballot.getBallotSize());
            randomMessageList.add(ballot.getBallotMessage(randomNumber));
        }
        return randomMessageList;
    }
}
