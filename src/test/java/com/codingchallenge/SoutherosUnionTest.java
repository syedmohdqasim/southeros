package com.codingchallenge;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

public class SoutherosUnionTest {
    private SoutherosUnion southerosUnion;
    private Kingdom airKingdom;
    private Kingdom iceKingdom;
    private Kingdom fireKingdom;
    private Kingdom waterKingdom;
    private CompetingKingdom spaceKingdomCompeting;
    private CompetingKingdom landKingdomCompeting;
    private ArrayList<CompetingKingdom> competingKingdoms;

    @Before
    public void setUp() {
        airKingdom = new Kingdom("Air", "Owl");
        iceKingdom = new Kingdom("Ice", "Mammoth");
        fireKingdom = Mockito.spy(new Kingdom("Fire", "Dragon"));
        waterKingdom = Mockito.spy(new Kingdom("Water", "Octopus"));
        spaceKingdomCompeting = Mockito.spy(new CompetingKingdom("Space", "Gorilla"));
        landKingdomCompeting = Mockito.spy(new CompetingKingdom("Land", "Panda"));
        competingKingdoms = new ArrayList<>();
        competingKingdoms.add(spaceKingdomCompeting);
        competingKingdoms.add(landKingdomCompeting);
        southerosUnion = new SoutherosUnion(initializeKingdoms(), competingKingdoms);
    }

    @Test
    public void conductElectionsShouldGiveAWinnerAfterNTries() throws IOException {

        Optional<Kingdom> kingdom = southerosUnion.conductElections();
        while (!kingdom.isPresent()) {
            southerosUnion.printResultsAfterRound();
            southerosUnion.removeNonTiedCompetingKingdoms();
            kingdom = southerosUnion.conductElections();
        }
        southerosUnion.printResultsAfterRound();
    }

    @Test
    public void removeNonTiedCompetingKingdoms() {
        ArrayList<CompetingKingdom> competingKingdomsSpy = Mockito.spy(competingKingdoms);

        Mockito.doReturn(List.of(airKingdom, waterKingdom)).when(spaceKingdomCompeting).getAllies();
        Mockito.doReturn(List.of(fireKingdom)).when(landKingdomCompeting).getAllies();

        southerosUnion.removeNonTiedCompetingKingdoms();

        assertEquals(1, this.competingKingdoms.size());
    }

    @Test
    public void shouldProcessEachOfTheBallotMessagesAndReturnWinner() throws IOException {
        BallotMessage ballotMessage = mock(BallotMessage.class);
        BallotMessage ballotMessage2 = mock(BallotMessage.class);
        when(ballotMessage.getSender()).thenReturn(spaceKingdomCompeting);
        when(ballotMessage.getReceiver()).thenReturn(waterKingdom);
        when(ballotMessage2.getSender()).thenReturn(landKingdomCompeting);
        when(ballotMessage2.getReceiver()).thenReturn(fireKingdom);
        when(fireKingdom.shouldGiveAllegiance(anyString())).thenReturn(false);
        when(waterKingdom.shouldGiveAllegiance(anyString())).thenReturn(true);

        Optional<Kingdom> kingdom = southerosUnion.decideRulerBasedOnAlliances(List.of(ballotMessage, ballotMessage2));

        assertEquals(spaceKingdomCompeting, kingdom.get());

    }

    @Test
    public void shouldGiveEmptyOptionalIfNoOneGetsClearMandate() throws IOException {
        BallotMessage ballotMessage = mock(BallotMessage.class);
        BallotMessage ballotMessage2 = mock(BallotMessage.class);
        when(ballotMessage.getSender()).thenReturn(spaceKingdomCompeting);
        when(ballotMessage.getReceiver()).thenReturn(waterKingdom);
        when(ballotMessage2.getSender()).thenReturn(landKingdomCompeting);
        when(ballotMessage2.getReceiver()).thenReturn(fireKingdom);
        when(fireKingdom.shouldGiveAllegiance(anyString())).thenReturn(true);
        when(waterKingdom.shouldGiveAllegiance(anyString())).thenReturn(true);

        Optional<Kingdom> kingdom = southerosUnion.decideRulerBasedOnAlliances(List.of(ballotMessage, ballotMessage2));

        assertFalse(kingdom.isPresent());

    }

    @Test
    public void printResultsShouldBePrintedForAllCompetingKingdoms() {
        southerosUnion.printResultsAfterRound();
        verify(spaceKingdomCompeting).getAllies();
        verify(spaceKingdomCompeting, times(2)).getName();
        verify(landKingdomCompeting).getAllies();
        verify(landKingdomCompeting, times(2)).getName();
    }

    private HashMap<String, Kingdom> initializeKingdoms() {
        HashMap<String, Kingdom> kingdoms = new HashMap<>();
        kingdoms.put("space", spaceKingdomCompeting);
        kingdoms.put("land", landKingdomCompeting);
        kingdoms.put("water", waterKingdom);
        kingdoms.put("ice", iceKingdom);
        kingdoms.put("fire", fireKingdom);
        kingdoms.put("air", airKingdom);
        return kingdoms;
    }
}