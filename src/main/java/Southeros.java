//Represents the universe Southeros

import java.util.ArrayList;
import java.util.List;

public class Southeros {
    private List<Kingdom> kingdoms;
    private String king;
    private List<Kingdom> alliesOfRuler;

    public Southeros() {
        king = "None";
        alliesOfRuler = new ArrayList<Kingdom>();
        kingdoms = new ArrayList<Kingdom>();
        initialize();
    }

    private void initialize() {
        kingdoms.add(new Kingdom("Space", "Gorilla"));
        kingdoms.add(new Kingdom("Land", "Panda"));
        kingdoms.add(new Kingdom("Water", "Octopus"));
        kingdoms.add(new Kingdom("Ice", "Mammoth"));
        kingdoms.add(new Kingdom("Fire", "Dragon"));
        kingdoms.add(new Kingdom("Air", "Owl"));
    }

    public String getKing() {
        return king;
    }

}
