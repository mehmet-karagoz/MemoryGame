package MemoryGame;

import java.io.Serializable;

/**
 * @author Mehmet Karagoz
 * @since 31.12.2020
 */

public class Cards implements Serializable {

    private final char value;
    private boolean prediction = false;

    public Cards(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public boolean isPrediction() {
        return prediction;
    }

    public void setPrediction(boolean prediction) {
        this.prediction = prediction;
    }
}