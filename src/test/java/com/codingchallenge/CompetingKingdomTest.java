package com.codingchallenge;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class CompetingKingdomTest {

    private CompetingKingdom competingKingdom;
    private Kingdom ally;
    private String kingdom1 = "kingdom1";
    private String emblem1 = "emblem1";

    @Before
    public void setUp() throws Exception {
        competingKingdom = new CompetingKingdom(kingdom1, emblem1);
        ally = mock(Kingdom.class);
    }

    @Test
    public void addAllyShouldAddAllyTpCompetingKindomsAllies() {
        Kingdom kingdom = mock(Kingdom.class);
        competingKingdom.addAlly(kingdom);
        competingKingdom.addAlly(ally);
        List<Kingdom> actualAllies = competingKingdom.getAllies();
        assertEquals(actualAllies.size(), 2);
        assertTrue(actualAllies.contains(kingdom));
        assertTrue(actualAllies.contains(ally));
    }

    @Test
    public void shouldReturnCorrectNameOfCompetingKingdom() {
        assertEquals(kingdom1,competingKingdom.getName());
    }

    @Test
    public void shouldReturnCorrectEmblemOfCompetingKingdom() {
        assertEquals(emblem1,competingKingdom.getEmblem());
    }

    @Test
    public void shouldNotGiveAllegianceToAnyone() {
        assertFalse(competingKingdom.shouldGiveAllegiance(competingKingdom.getEmblem()));
    }

    @Test
    public void clearAlliesShouldClearExistingAllies() {
        Kingdom kingdom = mock(Kingdom.class);
        competingKingdom.addAlly(kingdom);
        competingKingdom.addAlly(ally);
        List<Kingdom> actualAllies = competingKingdom.getAllies();
        assertEquals(actualAllies.size(), 2);

        competingKingdom.clearAllies();

        assertEquals(0,competingKingdom.getAllies().size());
    }
}