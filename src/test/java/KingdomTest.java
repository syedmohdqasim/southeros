import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class KingdomTest {

    private Kingdom kingdomOne;
    private String kingdomeOne = "kingdomeOne";
    private String animalOne = "animalOne";

    @Before
    public void setUp() throws Exception {
        kingdomOne = new Kingdom(kingdomeOne, animalOne);
    }

    @Test
    public void shouldReturnCorrectNameOfKingdom() {
        assertEquals(kingdomOne.getName(), kingdomeOne);
    }

    @Test
    public void shouldReturnCorrectNameOfEmblem() {
        assertEquals(kingdomOne.getEmblem(), animalOne);
    }
}