import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

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

    @Test
    public void southerosShouldMakeKingShanRulerIfMoreThan2KingdomsGiveAllegianceToHim() {
        String airKingdomName = "air";
        String fireKingdomName = "fire";
        String iceKingdomName = "ice";
        String secretMessage = "secretMessage";
        when(airKingdom.shouldGiveAllegianceToShan(secretMessage)).thenReturn(true);
        when(fireKingdom.shouldGiveAllegianceToShan(secretMessage)).thenReturn(true);
        when(iceKingdom.shouldGiveAllegianceToShan(secretMessage)).thenReturn(true);

        southeros.processMessagesForKingdomFromKingShan(airKingdomName, secretMessage);
        southeros.processMessagesForKingdomFromKingShan(fireKingdomName, secretMessage);
        southeros.processMessagesForKingdomFromKingShan(iceKingdomName, secretMessage);

        assertEquals(southeros.getKing(),"King Shan");
    }

    @Test
    public void southerosShouldNotMakeKingShanRulerIflessThan3KingdomsGiveAllegianceToHim() {
        String airKingdomName = "air";
        String fireKingdomName = "fire";
        String iceKingdomName = "ice";
        String secretMessage = "secretMessage";
        when(airKingdom.shouldGiveAllegianceToShan(secretMessage)).thenReturn(true);
        when(fireKingdom.shouldGiveAllegianceToShan(secretMessage)).thenReturn(true);
        when(iceKingdom.shouldGiveAllegianceToShan(secretMessage)).thenReturn(false);

        southeros.processMessagesForKingdomFromKingShan(airKingdomName, secretMessage);
        southeros.processMessagesForKingdomFromKingShan(fireKingdomName, secretMessage);
        southeros.processMessagesForKingdomFromKingShan(iceKingdomName, secretMessage);

        assertNotEquals(southeros.getKing(),"King Shan");
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