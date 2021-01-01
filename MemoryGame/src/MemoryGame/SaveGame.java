package MemoryGame;

import java.io.*;

/**
 * @author Mehmet Karagoz
 * @since 31.12.2020
 */

public class SaveGame {

    public static void saveGame(Cards[][] cards) {
        /*
        We use this method to save the game.
         */

        try (ObjectOutputStream outputStream =
                     new ObjectOutputStream(
                             new FileOutputStream("SavedGame.bin"))) {

            System.out.println("Game is saving...");
            outputStream.writeObject(cards);

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Cards[][] getSavedGame() {
        /*
        We use this method to get the saved game.
         */

        try (ObjectInputStream inputStream =
                     new ObjectInputStream(
                             new FileInputStream("SavedGame.bin"))) {

            return (Cards[][]) inputStream.readObject();

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            System.out.println("There is no class like that.");
            e.printStackTrace();
        }
        return null;
    }
}
