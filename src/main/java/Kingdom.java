//Represents a main.java.Kingdom in universe of Southeros
public class Kingdom {
    private String name;
    private String emblem;

    public Kingdom(String name, String emblem) {
        this.name = name;
        this.emblem = emblem;
    }

    public String getName() {
        return name;
    }

    public String getEmblem() {
        return emblem;
    }

    public boolean shouldGiveAllegianceToShan(String message) {
        return false;
    }
}
