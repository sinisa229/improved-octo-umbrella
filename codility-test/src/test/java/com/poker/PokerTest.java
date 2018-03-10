package com.poker;

import com.poker.hand.Card;
import com.poker.hand.Hand;
import com.poker.hand.analysis.HandAnalyzerImpl;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

// https://projecteuler.net/problem=54
public class PokerTest {

    @Test
    public void shouldCompareCardByCard() throws Exception {
        Hand hand = new Hand("AS KS QS JS TS 9S 8S 7S 6S 5S 4S 3S 2S");
        assertThat(hand.getCards(), contains(c("2S"), c("3S"), c("4S"), c("5S"), c("6S"), c("7S"), c("8S"), c("9S"), c("TS"), c("JS"), c("QS"), c("KS"), c("AS")));
    }

    @Test
    public void cardCounter() throws Exception {
        HandAnalyzerImpl handAnalyzerImpl = new HandAnalyzerImpl(new Hand("AS 2S AC"));
        assertTrue(handAnalyzerImpl.matchesCountPattern("12"));
    }

    @Test
    public void cardCounterFullHouse() throws Exception {
        HandAnalyzerImpl handAnalyzerImpl = new HandAnalyzerImpl(new Hand("AS 2S AS 2S AC"));
        assertTrue(handAnalyzerImpl.matchesCountPattern("23"));
    }

    @Test
    public void cardCounterFullHousePattern() throws Exception {
        HandAnalyzerImpl handAnalyzerImpl = new HandAnalyzerImpl(new Hand("AS 2S AS 2S AC"));
        assertTrue(handAnalyzerImpl.matchesCountPattern("23"));
    }

    @Test
    public void suiteMatcherShouldMatch() throws Exception {
        Hand hand = new Hand("AS KS 2S");
        assertTrue(new HandAnalyzerImpl(hand).sameSuite());
    }

    @Test
    public void suiteMatcherShouldNotMatch() throws Exception {
        Hand hand = new Hand("AS KH 2S");
        assertFalse(new HandAnalyzerImpl(hand).sameSuite());
    }

    @Test
    public void shouldEvaluateOrdered() throws Exception {
        assertTrue(new HandAnalyzerImpl(new Hand("2S 3S 4S")).areCardsOrdered());
    }

    @Test
    public void shouldEvaluateNotOrderedWhenDuplicatedValue() throws Exception {
        assertFalse(new HandAnalyzerImpl(new Hand("2S 2S 4S")).areCardsOrdered());
    }

    @Test
    public void shouldEvaluateNotOrderedWhenMissingValue() throws Exception {
        assertFalse(new HandAnalyzerImpl(new Hand("2S 5S 4S")).areCardsOrdered());
    }

    private Card c(String card) {
        return new Card(card);
    }

}