//Represents the conglomerate kingdoms of Southeros.

import java.io.IOException;
import java.util.*;

class SoutherosUnion {
    private HashMap<String, Kingdom> memberKingdoms;
    private List<CompetingKingdom> competingKingdoms;
    private HighPriest highPriest;
    private Ballot ballot;

    SoutherosUnion(HashMap<String, Kingdom> memberKingdoms, List<CompetingKingdom> competingKingdoms) {
        this.memberKingdoms = memberKingdoms;
        this.competingKingdoms = competingKingdoms;
        ballot = new Ballot();
        highPriest = new HighPriest();
        for (CompetingKingdom contestingKingdom : competingKingdoms) {
            memberKingdoms.put(contestingKingdom.getName().toLowerCase(), contestingKingdom);
        }
    }

    Optional<Kingdom> conductElections() throws IOException {
        ballot.getMessagesFromContestingKingdoms(competingKingdoms, memberKingdoms);
        List<BallotMessage> ballotMessages = highPriest.getBallotMessages(ballot);
        competingKingdoms.forEach(CompetingKingdom::clearAllies);
        return decideRulerBasedOnAlliances(ballotMessages);
    }

    Optional<Kingdom> decideRulerBasedOnAlliances(List<BallotMessage> ballotMessages) {
        for (BallotMessage ballotMessage : ballotMessages) {
            CompetingKingdom competingKingdom = (CompetingKingdom) memberKingdoms.get(ballotMessage.getSender().getName().toLowerCase());
            Kingdom receivingKingdom = memberKingdoms.get(ballotMessage.getReceiver().getName().toLowerCase());
            if (receivingKingdom.shouldGiveAllegiance(ballotMessage.getMessage())) {
                competingKingdom.addAlly(receivingKingdom);
            }
        }
        competingKingdoms.sort(Comparator.comparing(competingKingdom -> competingKingdom.getAllies().size()));
        Collections.reverse(competingKingdoms);
        if (competingKingdoms.size() == 1 || competingKingdoms.get(0).getAllies().size() > competingKingdoms.get(1).getAllies().size()) {
            return Optional.of(competingKingdoms.get(0));
        } else {
            return Optional.empty();
        }
    }

    void removeNonTiedCompetingKingdoms() {
        competingKingdoms.sort(Comparator.comparing(competingKingdom -> competingKingdom.getAllies().size()));
        Collections.reverse(competingKingdoms);
        competingKingdoms.removeIf(competingKingdom -> competingKingdom.getAllies().size() < competingKingdoms.get(0).getAllies().size());
    }

    void printResultsAfterRound() {
        for (CompetingKingdom competingKingdom : competingKingdoms) {
            System.out.println("Allies for " + competingKingdom.getName() + ": " + competingKingdom.getAllies().size());
        }
    }
}
