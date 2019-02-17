import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BallotTest {

    private Ballot ballot;
    private BallotMessage ballotMessage;

    @Before
    public void setUp() {
        ballotMessage = mock(BallotMessage.class);
        ballot = new Ballot();
    }

    @Test
    public void shouldBeAbleToaddandGetBallotMessage() {
        ballot.addBallotMessage(ballotMessage);
        assertEquals(ballotMessage, ballot.getBallotMessage(0));
        BallotMessage ballotMessage2 = mock(BallotMessage.class);
        ballot.addBallotMessage(ballotMessage2);
        assertEquals(ballotMessage2, ballot.getBallotMessage(1));
    }

    @Test
    public void getBallotSizeShouldReturnCorrectSize() {
        ballot.addBallotMessage(ballotMessage);
        assertEquals(Integer.valueOf(1),ballot.getBallotSize());
        ballot.addBallotMessage(ballotMessage);
        assertEquals(Integer.valueOf(2),ballot.getBallotSize());
    }

    @Test
    public void shouldGetMessagesForAllKingdomsForAllCompetingKingdoms() throws IOException {
        CompetingKingdom competingKingdom1 = mock(CompetingKingdom.class);
        CompetingKingdom competingKingdom2 = mock(CompetingKingdom.class);

        Kingdom kingdom = mock(Kingdom.class);
        Kingdom kingdom2 = mock(Kingdom.class);

        when(kingdom.getName()).thenReturn("kingdom1");
        when(kingdom2.getName()).thenReturn("kingdom2");
        when(competingKingdom1.getName()).thenReturn("kingdom3");
        when(competingKingdom2.getName()).thenReturn("kingdom4");

        HashMap<String, Kingdom> kingdomHashMap = new HashMap<>();
        kingdomHashMap.put(kingdom.getName(),kingdom);
        kingdomHashMap.put(kingdom2.getName(),kingdom2);
        kingdomHashMap.put(competingKingdom1.getName(),competingKingdom1);
        kingdomHashMap.put(competingKingdom2.getName(),competingKingdom2);
        ballot.getMessagesFromContestingKingdoms(List.of(competingKingdom1,competingKingdom2),kingdomHashMap);
        assertEquals(Integer.valueOf(150),ballot.getBallotSize());
    }
}