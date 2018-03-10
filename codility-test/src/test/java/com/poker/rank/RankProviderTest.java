package com.poker.rank;

import org.junit.Test;

import static com.poker.rank.RankType.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class RankProviderTest {

    @Test
    public void shouldReturnRoyalFlush() throws Exception {
        verifyRank("11111", true, true, true, "1", ROYAL_FLUSH);
    }

    @Test
    public void shouldReturnStraightFlush() throws Exception {
        verifyRank("11111", true, true, false, "1", STRAIGHT_FLUSH);
    }

    @Test
    public void shouldReturnFourOfAKind() throws Exception {
        verifyRank("14", false, false, null, "1", FOUR_OF_A_KIND);
    }

    @Test
    public void shouldReturnFullHouse() throws Exception {
        verifyRank("23", false, false, null, "1", FULL_HOUSE);
    }

    @Test
    public void shouldReturnFlush() throws Exception {
        verifyRank("11111", false, true, null, "1", FLUSH);
    }

    @Test
    public void shouldReturnStraight() throws Exception {
        verifyRank("11111", true, false, null, "1", STRAIGHT);
    }

    @Test
    public void shouldReturnThreeOfAKind() throws Exception {
        verifyRank("113", false, false, null, "1", THREE_OF_A_KIND);
    }

    @Test
    public void shouldReturnTwoOfAKind() throws Exception {
        verifyRank("122", false, false, null, "1", TWO_PAIRS);
    }

    @Test
    public void shouldReturnOnePair() throws Exception {
        verifyRank("1112", false, false, null, "1", ONE_PAIR);
    }

    @Test
    public void shouldReturnHighCard() throws Exception {
        verifyRank("11111", false, false, null, "1", HIGH_CARD);
    }

    private void verifyRank(final String countPattern, final boolean areCardsOrdered, final boolean sameSuite, final Boolean containsAce, final String valuePattern, final RankType royalFlush) {
        assertThat(getRankType(countPattern, areCardsOrdered, sameSuite, containsAce, valuePattern), equalTo(royalFlush));
    }

    private RankType getRankType(final String countPattern, final boolean areCardsOrdered, final boolean sameSuite, final Boolean containsAce, final String valuePattern) {
        return new RankProvider(cardProcessor(countPattern, areCardsOrdered, sameSuite, containsAce, valuePattern)).getHighestRank().getRankType();
    }

    private HandAnalyzer cardProcessor(final String countPattern, final Boolean areCardsOrdered, final Boolean sameSuite, final Boolean containsAce, final String valuePattern) {
        return new HandAnalyzer() {
            @Override
            public Boolean matchesCountPattern(final String s) {
                return countPattern.equals(s);
            }

            @Override
            public Boolean areCardsOrdered() {
                return areCardsOrdered;
            }

            @Override
            public Boolean sameSuite() {
                return sameSuite;
            }

            @Override
            public Boolean containsAce() {
                return containsAce;
            }

            @Override
            public String getValuePattern() {
                return valuePattern;
            }
        };
    }

}