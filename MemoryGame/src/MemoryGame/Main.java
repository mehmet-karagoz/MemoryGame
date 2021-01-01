package MemoryGame;

import java.io.File;
import java.util.Scanner;

/**
 * @author Mehmet Karagoz
 * @since 31.12.2020
 */

public class Main {

    private static Cards[][] cards = new Cards[4][4];
    private static final Scanner scanner = new Scanner(System.in);

    public static void getFromSaved() {

        /*
        We use this method to get a saved game if it exists.
         */

        File file = new File("SavedGame.bin");

        if (file.exists()) {

            System.out.print("There is a saved game. Would you like to "
                               + "continue where you left off ? (y/n) : ");
            String choice = scanner.nextLine();

            if (choice.equals("y")) {
                cards = SaveGame.getSavedGame();
                return;
            }
        }
        /*
        If it does not exist then we fill the array with default values.
         */

        cards[0][0] = new Cards('A');
        cards[0][1] = new Cards('B');
        cards[0][2] = new Cards('E');
        cards[0][3] = new Cards('E');
        cards[1][0] = new Cards('D');
        cards[1][1] = new Cards('F');
        cards[1][2] = new Cards('D');
        cards[1][3] = new Cards('C');
        cards[2][0] = new Cards('A');
        cards[2][1] = new Cards('B');
        cards[2][2] = new Cards('C');
        cards[2][3] = new Cards('F');
        cards[3][0] = new Cards('G');
        cards[3][1] = new Cards('H');
        cards[3][2] = new Cards('H');
        cards[3][3] = new Cards('G');
    }

    public static void guess() {

        /*
        We use this method to guess the cards.
         */
        Scanner scanner = new Scanner(System.in);

        System.out.print("First Prediction indexes: ");
        int i1 = scanner.nextInt();
        int j1 = scanner.nextInt();

        cards[i1][j1].setPrediction(true);
        gameBoard();

        System.out.print("Second Prediction indexes: ");
        int i2 = scanner.nextInt();
        int j2 = scanner.nextInt();

        if (cards[i1][j1].getValue() == cards[i2][j2].getValue()) {
            System.out.println();
            System.out.println("...Correct...");
            cards[i2][j2].setPrediction(true);
        }
        else {
            System.out.println();
            System.out.println("...Wrong...");
            cards[i1][j1].setPrediction(false);
        }
    }

    public static boolean gameIsOver() {
        /*
        We use this method to check the game is over or not.
         */

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (!cards[i][j].isPrediction()) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void gameBoard() {

        /*
        We use this method to display the game board.
         */
        System.out.println("__________________");

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (cards[i][j].isPrediction()) {
                    System.out.print("|" + cards[i][j].getValue() + "|  ");
                }
                else {
                    System.out.print("| |  ");
                }
            }
            System.out.println();
            System.out.println("__________________");

        }
    }


    public static void main(String[] args) {
        getFromSaved(); //First we check to see if there is a saved game.

        while (!gameIsOver()) { //To play the game until the user presses q
            gameBoard();

            System.out.print("Press q to exit. Press any key to continue...");
            String exit = scanner.nextLine();

            if (exit.equals("q")) {
                System.out.print("Do you want to save the game? (y/n) : ");
                String record = scanner.nextLine();

                if (record.equals("y")) {
                    SaveGame.saveGame(cards);
                }else {
                    System.out.println("The game was not saved.");
                }
                System.out.println("Quitting the game ...");
                break;
            }

            guess();
        }
    }


}
