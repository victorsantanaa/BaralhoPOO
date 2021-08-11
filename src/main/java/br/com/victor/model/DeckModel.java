package br.com.victor.model;

import javax.persistence.*;
import java.util.ArrayList;

public class DeckModel {

    String deck_id;
    boolean success;
    boolean shuffled;
    int remaining;
    ArrayList<Object> cards;

    public DeckModel() {
    }

    public DeckModel(boolean shuffled) {
        this.shuffled = shuffled;
    }

    public ArrayList<Object> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Object> cards) {
        this.cards = cards;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getDeckId() {
        return deck_id;
    }

    public void setDeckId(String deck_id) {
        this.deck_id = deck_id;
    }

    public boolean isShuffled() {
        return shuffled;
    }

    public void setShuffled(boolean shuffled) {
        this.shuffled = shuffled;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }
}
