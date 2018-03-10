package com.poker.hand;

import com.poker.hand.Card;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.stream;

@Data
public class Hand {

    private final List<Card> cards = new ArrayList<>();

    public Hand(String handString) {
        stream(handString.split("\\s")).forEach(s -> this.cards.add(new Card(s)));
        cards.sort(Card::compareTo);
    }

    public Card getHighest() {
        return cards.get(cards.size()-1);
    }

    public Card getLowest() {
        return cards.get(0);
    }

}
