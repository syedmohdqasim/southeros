import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

public class HighPriestTest {

    private Ballot ballot;
    private HighPriest highPriest;

    @Before
    public void setUp() throws Exception {
        ballot = mock(Ballot.class);
        highPriest = new HighPriest();
    }

    @Test
    public void getBallotMessages() {
        BallotMessage ballotMessage = mock(BallotMessage.class);
        when(ballot.getBallotSize()).thenReturn(10);
        when(ballot.getBallotMessage(anyInt())).thenReturn(ballotMessage);

        List<BallotMessage> ballotMessages = highPriest.getBallotMessages(ballot);

        verify(ballot, times(6)).getBallotMessage(anyInt());
        assertEquals(6, ballotMessages.size());
        assertEquals(ballotMessage, ballotMessages.get(0));
        assertEquals(ballotMessage, ballotMessages.get(1));
        assertEquals(ballotMessage, ballotMessages.get(2));
        assertEquals(ballotMessage, ballotMessages.get(3));
        assertEquals(ballotMessage, ballotMessages.get(4));
        assertEquals(ballotMessage, ballotMessages.get(5));
    }
}