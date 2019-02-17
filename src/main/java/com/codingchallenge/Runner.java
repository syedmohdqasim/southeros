package com.codingchallenge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;

import static java.lang.System.exit;

public class Runner {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));


    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("Please run with command \"java -jar target/com.codingchallenge.Southeros-1.0.jar one\" to run sample problem one");
            System.out.println("or command \"java -jar target/com.codingchallenge.Southeros-1.0.jar two\" to run sample problem two");
            exit(0);
        }
        if (args[0].equals("one"))
            RunSampleProblem1();
        else if(args[0].equals("two"))
            RunSampleProblem2();
        else System.out.println("Please enter \"one\" or \"two\"");
    }

    private static void RunSampleProblem2() {

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
            if(input.length<2){
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
