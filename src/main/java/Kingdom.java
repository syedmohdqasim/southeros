//Represents a main.java.Kingdom in universe of Southeros
class Kingdom {
    private String name;
    private String emblem;

    Kingdom(String name, String emblem) {
        this.name = name;
        this.emblem = emblem;
    }

    String getName() {
        return name;
    }

    String getEmblem() {
        return emblem;
    }

    boolean shouldGiveAllegiance(String message) {
        return containsAllLettersOfEmblem(message);
    }

    private boolean containsAllLettersOfEmblem(String message) {
        message = message.toLowerCase();
        for (char letter : emblem.toLowerCase().toCharArray()) {
            String character = String.valueOf(letter);
            if (message.contains(character)) {
                message = message.replaceFirst(character, "");
            } else {
                return false;
            }
        }
        return true;
    }
}
