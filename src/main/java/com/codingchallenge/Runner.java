package com.codingchallenge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static java.lang.System.exit;

public class Runner {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static HashMap<String, Kingdom> kingdoms;


    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("Please run with command \"java -jar target/com.codingchallenge.Southeros-1.0.jar one\" to run sample problem one");
            System.out.println("or command \"java -jar target/com.codingchallenge.Southeros-1.0.jar two\" to run sample problem two");
            exit(0);
        }
        switch (args[0]) {
            case "one":
                kingdoms = initialize();
                RunSampleProblem1();
                break;
            case "two":
                kingdoms = initialize();
                RunSampleProblem2();
                break;
            default:
                System.out.println("Please enter \"one\" or \"two\"");
                break;
        }
    }

    private static void RunSampleProblem2() throws IOException {
        SoutherosUnion southerosUnion;
        try {
            southerosUnion = new SoutherosUnion(kingdoms, getCompetingKingdomsFromUser());

            Optional<Kingdom> kingdom = southerosUnion.conductElections();
            Integer round = 1;
            while (!kingdom.isPresent()) {
                southerosUnion.printResultsAfterRound(round);
                southerosUnion.removeNonTiedCompetingKingdoms();
                kingdom = southerosUnion.conductElections();
                round++;
            }
            southerosUnion.printResultsAfterRound(round);

            System.out.println("Who is the ruler of Southeros: ->" + kingdom.get().getName());
            List<Kingdom> allies = ((CompetingKingdom) kingdom.get()).getAllies();
            StringBuilder allAllies = new StringBuilder();
            for (Kingdom ally : allies) {
                allAllies.append(ally.getName()).append(" ");
            }
            System.out.println("Allies of Ruler: ->" + allAllies);
        } catch (WrongInputFormatException | NoSuchKingdomException e) {
            System.out.println(e.getMessage());
        }
    }

    private static List<CompetingKingdom> getCompetingKingdomsFromUser() throws IOException, WrongInputFormatException, NoSuchKingdomException {
        ArrayList<CompetingKingdom> competingKingdoms = new ArrayList<>();
        System.out.println("Please Input the competing Kingdoms space seperated");
        String s = bufferedReader.readLine();
        String[] kingdomNames = s.split(" ");
        if (kingdomNames.length < 1) {
            throw new WrongInputFormatException("Wrong Input Format, Please type kingdom names seperated by space");
        }
        for (String kingdomName : kingdomNames) {
            Kingdom kingdom = kingdoms.get(kingdomName.toLowerCase());
            if (kingdom == null) {
                throw new NoSuchKingdomException("Please enter a correct Kingdom.");
            }
            competingKingdoms.add(new CompetingKingdom(kingdom.getName(), kingdom.getEmblem()));
        }
        return competingKingdoms;
    }

    private static void RunSampleProblem1() throws IOException {
        Southeros southeros = new Southeros(initialize());
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
            if (input.length < 2) {
                throw new WrongInputFormatException("Wrong Input Format Exception");
            }
            southeros.processMessagesForKingdomFromKingShan(input[0], input[1]);
        } catch (NoSuchKingdomException | WrongInputFormatException e) {
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
        HashMap<String, Kingdom> kingdoms = new HashMap<>();
        kingdoms.put("space", new Kingdom("Space", "Gorilla"));
        kingdoms.put("land", new Kingdom("Land", "Panda"));
        kingdoms.put("water", new Kingdom("Water", "Octopus"));
        kingdoms.put("ice", new Kingdom("Ice", "Mammoth"));
        kingdoms.put("fire", new Kingdom("Fire", "Dragon"));
        kingdoms.put("air", new Kingdom("Air", "Owl"));
        return kingdoms;
    }
}
