package com.codingchallenge;//Represents an com.codingchallenge.Ballot containing messages

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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

    Integer getBallotSize() {
        return ballotMessages.size();
    }

    void getMessagesFromContestingKingdoms(List<CompetingKingdom> contestingKingdoms, HashMap<String, Kingdom> memberKingdoms) throws IOException {
        ballotMessages.clear();
        String messages[] = getMessagesFromSampleFile();
        for (CompetingKingdom competingKingdom : contestingKingdoms) {
            for (Kingdom receivingKingdom : memberKingdoms.values()) {
                if (!receivingKingdom.getName().equals(competingKingdom.getName())) {
                    for (String message : messages) {
                        ballotMessages.add(new BallotMessage(competingKingdom, receivingKingdom, message));
                    }
                }
            }
        }
    }

    private String[] getMessagesFromSampleFile() throws IOException {

        File file = new File("src/main/resources/sampleMessages.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] data = new byte[(int) file.length()];
        fileInputStream.read(data);
        fileInputStream.close();

        String fileData = new String(data, "UTF-8");
        return fileData.split("\",\\n\"");
    }
}
