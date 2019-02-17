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

    @Test
    public void shouldGiveAllegianceWhenMessageStringContainsEmblem() {
        assertTrue(kingdomOne.shouldGiveAllegiance("animalOne"));
    }

    @Test
    public void shouldNotGiveAllegianceWhenMessageStringContainsEmblem() {
        assertFalse(kingdomOne.shouldGiveAllegiance("aimalOne"));
    }

    @Test
    public void containsAllLettersOfEmblemShouldCheckForDoubleLetters() {
        assertTrue(kingdomOne.shouldGiveAllegiance("annimalOe"));
    }

    @Test
    public void containsAllLettersOfEmblemShouldCheckForAllLetters() {
        assertFalse(kingdomOne.shouldGiveAllegiance("annimalO"));
    }

    @Test
    public void containsAllLettersOfEmblemShouldShouldIgnoreCapitalizationLetters() {
        assertTrue(kingdomOne.shouldGiveAllegiance("annimaLOe"));
    }

    @Test
    public void containsAllLettersOfEmblemShouldShouldIgnoreSpecialLiterals() {
        assertTrue(kingdomOne.shouldGiveAllegiance("annim a`L! Oe"));
    }
}