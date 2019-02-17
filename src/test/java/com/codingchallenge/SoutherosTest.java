package com.codingchallenge;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SoutherosTest {

    private Southeros southeros;
    private Kingdom airKingdom;
    private Kingdom iceKingdom;
    private Kingdom fireKingdom;

    @Before
    public void setUp() {
        airKingdom = mock(Kingdom.class);
        iceKingdom = mock(Kingdom.class);
        fireKingdom = mock(Kingdom.class);
        southeros = new Southeros(initializeKingdoms());
    }

    @Test
    public void shouldReturnKingOfSoutheros() {
        assertEquals(southeros.getKing(), "None");
    }

    @Test
    public void processMessageShouldAddkingdomToAlliesIfTheyContainSecretCode() throws NoSuchKingdomException {
        String secretMessage = "secretMessage";
        when(airKingdom.shouldGiveAllegiance(secretMessage)).thenReturn(true);
        String airKingdom = "air";
        southeros.processMessagesForKingdomFromKingShan(airKingdom, secretMessage);
        assertTrue(southeros.getAlliesOfRuler().contains(this.airKingdom));
    }

    @Test
    public void shouldNotAddTheKingdomToAlliesItDoesNotHaveSecretMessage() throws NoSuchKingdomException {
        String secretMessage = "secretMessage";
        when(airKingdom.shouldGiveAllegiance(secretMessage)).thenReturn(false);
        String airKingdom = "air";
        southeros.processMessagesForKingdomFromKingShan(airKingdom, secretMessage);
        assertFalse(southeros.getAlliesOfRuler().contains(this.airKingdom));
    }

    @Test
    public void southerosShouldMakeKingShanRulerIfMoreThan2KingdomsGiveAllegianceToHim() throws NoSuchKingdomException {
        String airKingdomName = "air";
        String fireKingdomName = "fire";
        String iceKingdomName = "ice";
        String secretMessage = "secretMessage";
        when(airKingdom.shouldGiveAllegiance(secretMessage)).thenReturn(true);
        when(fireKingdom.shouldGiveAllegiance(secretMessage)).thenReturn(true);
        when(iceKingdom.shouldGiveAllegiance(secretMessage)).thenReturn(true);

        southeros.processMessagesForKingdomFromKingShan(airKingdomName, secretMessage);
        southeros.processMessagesForKingdomFromKingShan(fireKingdomName, secretMessage);
        southeros.processMessagesForKingdomFromKingShan(iceKingdomName, secretMessage);

        assertEquals(southeros.getKing(), "King Shan");
    }

    @Test
    public void southerosShouldNotMakeKingShanRulerIflessThan3KingdomsGiveAllegianceToHim() throws NoSuchKingdomException {
        String airKingdomName = "air";
        String fireKingdomName = "fire";
        String iceKingdomName = "ice";
        String secretMessage = "secretMessage";
        when(airKingdom.shouldGiveAllegiance(secretMessage)).thenReturn(true);
        when(fireKingdom.shouldGiveAllegiance(secretMessage)).thenReturn(true);
        when(iceKingdom.shouldGiveAllegiance(secretMessage)).thenReturn(false);

        southeros.processMessagesForKingdomFromKingShan(airKingdomName, secretMessage);
        southeros.processMessagesForKingdomFromKingShan(fireKingdomName, secretMessage);
        southeros.processMessagesForKingdomFromKingShan(iceKingdomName, secretMessage);

        assertNotEquals(southeros.getKing(), "King Shan");
    }

    @Test(expected = NoSuchKingdomException.class)
    public void processMessageShouldThrowExceptionIfNoSuchKingdomExists() throws NoSuchKingdomException {
        String secretMessage = "secretMessage";
        when(airKingdom.shouldGiveAllegiance(secretMessage)).thenReturn(true);
        String airKingdom = "airo";
        southeros.processMessagesForKingdomFromKingShan(airKingdom, secretMessage);
        assertTrue(southeros.getAlliesOfRuler().contains(this.airKingdom));
    }

    private HashMap<String, Kingdom> initializeKingdoms() {
        HashMap<String, Kingdom> kingdoms = new HashMap<String, Kingdom>();
        kingdoms.put("space", mock(Kingdom.class));
        kingdoms.put("land", mock(Kingdom.class));
        kingdoms.put("water", mock(Kingdom.class));
        kingdoms.put("ice", iceKingdom);
        kingdoms.put("fire", fireKingdom);
        kingdoms.put("air", airKingdom);
        return kingdoms;
    }
}