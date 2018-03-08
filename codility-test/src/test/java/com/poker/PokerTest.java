package com.poker;

import lombok.Data;
import org.junit.Test;

import java.util.*;
import java.util.stream.Stream;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

// https://projecteuler.net/problem=54
public class PokerTest {

//    private enum Rank {
//        HIGH_CARD(11111 && notSameSuite() && notOrdered()),
//        ONE_PAIR(1112 && notSameSuite()),
//        TWO_PAIRS(122 && notSameSuite()),
//        THREE_OF_A_KIND(113 && notSameSuite()),
//        STRAIGHT(11111 && notSameSuite() && ordered()),
//        FLUSH(11111 && sameSuite()),
//        FULL_HOUSE(23 && notSameSuite()),
//        FOUR_OF_A_KIND(14 && notSameSuite()),
//        STRAIGHT_FLUSH(11111 && sameSuite() && ordered()),
//        ROYAL_FLUSH(11111 && sameSuite() && ordered() && max == ace)
//    }

    @Test
    public void shouldCompareCardByCard() throws Exception {
        List<Card> cards = cards("AS", "KS", "QS", "JS", "TS", "9S", "8S", "7S", "6S", "5S", "4S", "3S", "2S");
        Collections.sort(cards);
        assertThat(cards, contains(c("2S"), c("3S"), c("4S"), c("5S"), c("6S"), c("7S"), c("8S"), c("9S"), c("TS"), c("JS"), c("QS"), c("KS"), c("AS")));
    }

    @Test
    public void cardCounter() throws Exception {
        CardCounter cardCounter = new CardCounter();
        cardCounter.addAll(c("AS"), c("2S"), c("AC"));
        assertThat(cardCounter.getValueCounts(), contains(1, 2));
    }

    @Test
    public void cardCounterFullHouse() throws Exception {
        CardCounter cardCounter = new CardCounter();
        cardCounter.addAll(c("AS"), c("2S"), c("AS"), c("2S"), c("AC"));
        assertThat(cardCounter.getValueCounts(), contains(2, 3));
    }

    @Test
    public void cardCounterFullHousePattern() throws Exception {
        CardCounter cardCounter = new CardCounter();
        cardCounter.addAll(c("AS"), c("2S"), c("AS"), c("2S"), c("AC"));
        assertThat(cardCounter.getCountsPattern(), equalTo("23"));
    }

    @Test
    public void suiteMatcherShouldMatch() throws Exception {
        assertTrue(new SuiteMatcher().areAllSameSuit(cards("AS", "KS","2S")));
    }

    @Test
    public void suiteMatcherShouldNotMatch() throws Exception {
        assertFalse(new SuiteMatcher().areAllSameSuit(cards("AS", "KH","2S")));
    }

    private List<Card> cards(String... cardStrings) {
        return stream(cardStrings).map(this::c).collect(toList());
    }

    private Card c(String card) {
        return new Card(card);
    }

    private enum Value {
        V_2(2, "Two"),
        V_3(3, "Three"),
        V_4(4, "Four"),
        V_5(5, "Four"),
        V_6(6, "Six"),
        V_7(7, "Seven"),
        V_8(8, "Eight"),
        V_9(9, "Nine"),
        V_T(10, "Ten"),
        V_J(11, "Jack"),
        V_Q(12, "Queen"),
        V_K(13, "King"),
        V_A(14, "Ace");

        private final Integer weight;

        private final String description;

        Value(Integer weight, String description) {
            this.weight = weight;
            this.description = description;
        }

        public Integer getWeight() {
            return weight;
        }


    }

    private static enum Suit {
        H,
        D,
        C,
        S
    }

    @Data
    private static class Card implements Comparable<Card> {

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

    private class SuiteMatcher {

        public boolean areNotSameSuit(List<Card> cards) {
            return areAllSameSuit(cards).equals(false);
        }

        public Boolean areAllSameSuit(List<Card> cards) {
            final long distinctSuitCount = getDistinctSuits(cards).count();
            return distinctSuitCount == 1;
        }

        private Stream<Suit> getDistinctSuits(final List<Card> cards) {
            return cards.stream().map(Card::getSuit).distinct();
        }

    }

    private static class CardCounter {

        private final Map<Value, CountResult> valueCounts = new TreeMap<>(Comparator.reverseOrder());

        public void addAll(Card... c) {
            stream(c).forEach(this::add);
        }

        public void add(Card c) {
            valueCounts.putIfAbsent(c.value, new CountResult(c.value, 0));
            valueCounts.computeIfPresent(c.value, (value, countResult) -> new CountResult(c.value, countResult.count+1));
        }

        public List<Integer> getValueCounts() {
            return valueCounts.values().stream().map(value -> value.count).sorted().collect(toList());
        }

        public String getCountsPattern() {
            return getValueCounts().stream().map(integer -> integer.toString()).collect(joining());
        }

    }

    @Data
    private static class CountResult {
        private final Value value;
        private final Integer count;
    }

}