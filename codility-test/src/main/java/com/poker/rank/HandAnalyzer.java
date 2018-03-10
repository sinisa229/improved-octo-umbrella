package com.poker.rank;

public interface HandAnalyzer {
    Boolean matchesCountPattern(String s);

    Boolean areCardsOrdered();

    Boolean sameSuite();

    Boolean containsAce();

    String getValuePattern();

    default Boolean cardsUnordered() {
        return areCardsOrdered().equals(false);
    }

    default Boolean notSameSuite() {
        return sameSuite().equals(false);
    }

    default Boolean doesNotContainAce() {
        return containsAce().equals(false);
    }

    default Integer getValuePatternAsInt() {
        return new Integer(getValuePattern());
    }
}
