import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

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
}