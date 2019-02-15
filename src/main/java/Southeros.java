//Represents the universe Southeros

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Southeros {
    private HashMap<String, Kingdom> kingdoms;
    private String king;
    private List<Kingdom> alliesOfRuler;

    Southeros(HashMap<String, Kingdom> kingdoms) {
        king = "None";
        alliesOfRuler = new ArrayList<Kingdom>();
        this.kingdoms = kingdoms;
    }

    String getKing() {
        return king;
    }

    List<Kingdom> getAlliesOfRuler() {
        return alliesOfRuler;
    }

    void processMessagesForKingdomFromKingShan(String kingdomName, String message) {
        if (kingdoms.get(kingdomName.toLowerCase()).shouldGiveAllegianceToShan(message)) {
            alliesOfRuler.add(kingdoms.get(kingdomName.toLowerCase()));
        }
        if(alliesOfRuler.size()>=3){
            king = "King Shan";
        }
    }

}
