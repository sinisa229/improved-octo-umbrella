package com.poker.hand;

public enum Suit {
    H("Hearts"),
    D("Diamonds"),
    C("Clubs"),
    S("Spades");

    private final String description;

    Suit(final String description) {
        this.description = description;
    }

}
