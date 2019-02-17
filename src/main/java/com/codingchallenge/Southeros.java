package com.codingchallenge;//Represents the universe com.codingchallenge.Southeros

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Southeros {
    private HashMap<String, Kingdom> kingdoms;
    private String king;
    private List<Kingdom> alliesOfRuler;

    Southeros(HashMap<String, Kingdom> kingdoms) {
        king = "None";
        alliesOfRuler = new ArrayList<>();
        this.kingdoms = kingdoms;
    }

    String getKing() {
        return king;
    }

    List<Kingdom> getAlliesOfRuler() {
        return alliesOfRuler;
    }

    void processMessagesForKingdomFromKingShan(String kingdomName, String message) throws NoSuchKingdomException {
        Kingdom kingdom = kingdoms.get(kingdomName.toLowerCase());
        if(kingdom==null){
            throw new NoSuchKingdomException("No Such Kindom Exists Please Try Again!!!");
        }
        if (kingdom.shouldGiveAllegiance(message)) {
            alliesOfRuler.add(kingdom);
        }
        if(alliesOfRuler.size()>=3){
            king = "King Shan";
        }
    }

}
