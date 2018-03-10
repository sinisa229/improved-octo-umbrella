package com.poker.hand;

import lombok.Data;

@Data
public class Card implements Comparable<Card> {

    private final Value value;
    private final Suit suit;

    public Card(String card) {
        this.value = Value.valueOf("V_" + card.substring(0, 1));
        this.suit = Suit.valueOf(card.substring(1, 2));
    }

    @Override
    public int compareTo(Card o) {
        return this.value.compareTo(o.value);
    }

}
