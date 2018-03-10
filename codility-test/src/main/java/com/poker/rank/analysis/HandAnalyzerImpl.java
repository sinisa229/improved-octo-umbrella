package com.poker.rank.analysis;

import com.poker.hand.Card;
import com.poker.hand.Hand;
import com.poker.hand.Suit;
import com.poker.hand.Value;
import com.poker.rank.HandAnalyzer;
import lombok.Data;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class HandAnalyzerImpl implements HandAnalyzer {

    private final Hand hand;
    private final Map<Value, CountResult> valueCounts = new TreeMap<>(Comparator.reverseOrder());

    public HandAnalyzerImpl(final Hand hand) {
        this.hand = hand;
        hand.getCards().forEach(this::add);
    }

    @Override
    public Boolean matchesCountPattern(String countPattern) {
        return countPattern.equals(getCountsPattern());
    }

    @Override
    public Boolean areCardsOrdered() {
        return areAllCardsDifferentValues() && (getHighestWeight() - getLowestWeight() == hand.getCards().size() - 1);
    }

    @Override
    public Boolean sameSuite() {
        final long distinctSuitCount = getDistinctSuits().count();
        return distinctSuitCount == 1;
    }

    @Override
    public Boolean containsAce() {
        return valueCounts.containsKey(Value.V_A);
    }

    @Override
    public String getValuePattern() {
        //TODO implement
        return null;
    }

    private List<Integer> getValueCounts() {
        return getSorted().collect(toList());
    }

    private String getCountsPattern() {
        return getValueCounts().stream().map(Object::toString).collect(joining());
    }

    private Integer getLowestWeight() {
        return hand.getLowest().getValue().getWeight();
    }

    private Integer getHighestWeight() {
        return hand.getHighest().getValue().getWeight();
    }

    private boolean areAllCardsDifferentValues() {
        return getCountsPattern().endsWith("1");
    }

    private void add(Card c) {
        valueCounts.putIfAbsent(c.getValue(), new CountResult(c.getValue(), 0));
        valueCounts.computeIfPresent(c.getValue(), (value, countResult) -> new CountResult(c.getValue(), countResult.count+1));
    }

    private Stream<Integer> getSorted() {
        return valueCounts.values().stream().map(value -> value.count).sorted();
    }

    private Stream<Suit> getDistinctSuits() {
        return hand.getCards().stream().map(Card::getSuit).distinct();
    }

    @Data
    private static class CountResult {
        private final Value value;
        private final Integer count;
    }

}
