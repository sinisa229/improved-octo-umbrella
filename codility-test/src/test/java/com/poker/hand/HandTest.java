package com.poker.hand;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

public class HandTest {

    @Test
    public void shouldHoldSortedCards() throws Exception {
        Hand hand = new Hand("AS KS QS JS TS 9S 8S 7S 6S 5S 4S 3S 2S");
        assertThat(hand.getCards(), contains(c("2S"), c("3S"), c("4S"), c("5S"), c("6S"), c("7S"), c("8S"), c("9S"), c("TS"), c("JS"), c("QS"), c("KS"), c("AS")));
    }

    private Card c(String card) {
        return new Card(card);
    }

}