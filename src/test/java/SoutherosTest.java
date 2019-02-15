import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SoutherosTest {

    private Southeros southeros;
    @Before
    public void setUp() throws Exception {
        southeros = new Southeros();
    }

    @Test
    public void shouldReturnKingOfSoutheros() {
        assertEquals(southeros.getKing(),"None");
    }

}