package com.codingchallenge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;

import static java.lang.System.exit;

public class Runner {
    public static void main(String[] args) throws IOException {
        Southeros southeros = new Southeros(initialize());
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        char choice;
        while (true) {
            System.out.println("Please Enter the number infront of the choice to proceed:\n");
            System.out.println("1.Press 1 to know the Ruler of Southeros:\n");
            System.out.println("2.Press 2 to know the Allies of the Ruler:\n");
            System.out.println("3.Press 3 to Input Messages to kingdoms from King Shan:\n");
            System.out.println("Please Press q to exit:\n");
            choice = bufferedReader.readLine().trim().charAt(0);
            switch (choice) {
                case '1':
                    System.out.println(southeros.getKing());
                    break;
                case '2':
                    printAllies(southeros);
                    break;
                case '3':
                    getInput(southeros, bufferedReader);
                    break;
                case 'q':
                    exit(0);
                default:
            }
        }
    }

    private static void getInput(Southeros southeros, BufferedReader bufferedReader) throws IOException {
        String[] input = bufferedReader.readLine().split(",");
        try {
            //todo check for argument 1 exists.
            southeros.processMessagesForKingdomFromKingShan(input[0], input[1]);
        } catch (NoSuchKingdomException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void printAllies(Southeros southeros) {
        List<Kingdom> alliesOfRuler = southeros.getAlliesOfRuler();
        String output = "";
        if (alliesOfRuler.size() > 2) {
            for (Kingdom kingdom : alliesOfRuler) {
                output = output.concat(kingdom.getName());
                output = output.concat(",");
            }
            output = output.concat(".");
            output = output.replace(",.", ".");
        } else {
            output = "None";
        }
        System.out.println(output);
    }

    private static HashMap<String, Kingdom> initialize() {
        HashMap<String, Kingdom> kingdoms = new HashMap<String, Kingdom>();
        kingdoms.put("space", new Kingdom("Space", "Gorilla"));
        kingdoms.put("land", new Kingdom("Land", "Panda"));
        kingdoms.put("water", new Kingdom("Water", "Octopus"));
        kingdoms.put("ice", new Kingdom("Ice", "Mammoth"));
        kingdoms.put("fire", new Kingdom("Fire", "Dragon"));
        kingdoms.put("air", new Kingdom("Air", "Owl"));
        return kingdoms;
    }
}
