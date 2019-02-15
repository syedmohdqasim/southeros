import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class SoutherosTest {

    private Southeros southeros;
    private Kingdom airKingdom;

    @Before
    public void setUp() {
        airKingdom = mock(Kingdom.class);
        southeros = new Southeros(initializeKingdoms());
    }

    @Test
    public void shouldReturnKingOfSoutheros() {
        assertEquals(southeros.getKing(), "None");
    }

    @Test
    public void processMessageShouldAddkingdomToAlliesIfTheyContainSecretCode() {
        String secretMessage = "secretMessage";
        when(airKingdom.shouldGiveAllegianceToShan(secretMessage)).thenReturn(true);
        String airKingdom = "air";
        southeros.processMessagesForKingdomFromKingShan(airKingdom, secretMessage);
        assertTrue(southeros.getAlliesOfRuler().contains(this.airKingdom));
    }

    @Test
    public void shouldNotAddTheKingdomToAlliesItDoesNotHaveSecretMessage() {
        String secretMessage = "secretMessage";
        when(airKingdom.shouldGiveAllegianceToShan(secretMessage)).thenReturn(false);
        String airKingdom = "air";
        southeros.processMessagesForKingdomFromKingShan(airKingdom, secretMessage);
        assertFalse(southeros.getAlliesOfRuler().contains(this.airKingdom));
    }

    private HashMap<String, Kingdom> initializeKingdoms() {
        HashMap<String, Kingdom> kingdoms = new HashMap<String, Kingdom>();
        kingdoms.put("space", mock(Kingdom.class));
        kingdoms.put("land", mock(Kingdom.class));
        kingdoms.put("water", mock(Kingdom.class));
        kingdoms.put("ice", mock(Kingdom.class));
        kingdoms.put("fire", mock(Kingdom.class));
        kingdoms.put("air", airKingdom);
        return kingdoms;
    }
}