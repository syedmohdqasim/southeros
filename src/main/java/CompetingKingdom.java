//Represents A kingdom competing tto become the Ruler.
import java.util.ArrayList;
import java.util.List;

class CompetingKingdom extends Kingdom {

    private List<Kingdom> allies;

    CompetingKingdom(String name, String emblem) {
        super(name, emblem);
        allies = new ArrayList<>();
    }

    List<Kingdom> getAllies() {
        return allies;
    }

    void addAlly(Kingdom ally) {
        this.allies.add(ally);
    }

    @Override
    boolean shouldGiveAllegiance(String message) {
        return false;
    }
}
