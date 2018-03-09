package com.poker;

import lombok.Data;
import org.junit.Test;

import java.util.Optional;

import static com.poker.RankTest.RankType.FLUSH;
import static com.poker.RankTest.RankType.FOUR_OF_A_KIND;
import static com.poker.RankTest.RankType.FULL_HOUSE;
import static com.poker.RankTest.RankType.HIGH_CARD;
import static com.poker.RankTest.RankType.ONE_PAIR;
import static com.poker.RankTest.RankType.ROYAL_FLUSH;
import static com.poker.RankTest.RankType.STRAIGHT;
import static com.poker.RankTest.RankType.STRAIGHT_FLUSH;
import static com.poker.RankTest.RankType.THREE_OF_A_KIND;
import static com.poker.RankTest.RankType.TWO_PAIRS;
import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;

public class RankTest {

    @Test
    public void rankTypeValuesShouldBeOrdered() throws Exception {
        assertThat(asList(RankType.values()), contains(ROYAL_FLUSH, STRAIGHT_FLUSH, FOUR_OF_A_KIND, FULL_HOUSE, FLUSH, STRAIGHT, THREE_OF_A_KIND, TWO_PAIRS, ONE_PAIR, HIGH_CARD));
    }

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
        return new RankFactory(cardProcessor(countPattern, areCardsOrdered, sameSuite, containsAce, valuePattern)).getHighestRank().getRankType();
    }

    private CardProcessor cardProcessor(final String countPattern, final Boolean areCardsOrdered, final Boolean sameSuite, final Boolean containsAce, final String valuePattern) {
        return new CardProcessor() {
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

    interface CardProcessor {
        Boolean matchesCountPattern(String s);
        Boolean areCardsOrdered();
        Boolean sameSuite();
        Boolean containsAce();
        String getValuePattern();

        default Boolean cardsUnordered() {return areCardsOrdered().equals(false);}
        default Boolean notSameSuite() {return sameSuite().equals(false);}
        default Boolean doesNotContainAce() {return containsAce().equals(false);}
        default Integer getValuePatternAsInt() {return new Integer(getValuePattern());}
    }

    private class RankFactory {

        private final CardProcessor cardProcessor;

        public RankFactory(CardProcessor cardProcessor) {
            this.cardProcessor = cardProcessor;
        }

        public Rank getHighestRank() {
            final Optional<RankType> rankType = stream(RankType.values()).filter(rt -> rt.match(cardProcessor)).findFirst();
            return new Rank(rankType.orElseThrow(() -> new RuntimeException("No type matches cardProcessor: " + cardProcessor)), cardProcessor.getValuePatternAsInt());
        }

    }

    @Data
    class Rank {
        private final RankType rankType;
        private final Integer getRankedCards;
    }


    public enum RankType {
        ROYAL_FLUSH(10) {
            @Override
            public boolean match(final CardProcessor cp) {
                return cp.matchesCountPattern("11111") && cp.sameSuite() && cp.areCardsOrdered() && cp.containsAce();
            }
        },
        STRAIGHT_FLUSH(9) {
            @Override
            public boolean match(final CardProcessor cp) {
                return cp.matchesCountPattern("11111") && cp.sameSuite() && cp.areCardsOrdered() && cp.doesNotContainAce();
            }
        },
        FOUR_OF_A_KIND(8) {
            @Override
            public boolean match(final CardProcessor cp) {
                return cp.matchesCountPattern("14") && cp.notSameSuite() && cp.cardsUnordered();
            }
        },
        FULL_HOUSE(7) {
            @Override
            public boolean match(final CardProcessor cp) {
                return cp.matchesCountPattern("23") && cp.notSameSuite() && cp.cardsUnordered();
            }
        },
        FLUSH(6) {
            @Override
            public boolean match(final CardProcessor cp) {
                return cp.matchesCountPattern("11111") && cp.sameSuite() && cp.cardsUnordered();
            }
        },
        STRAIGHT(5) {
            @Override
            public boolean match(final CardProcessor cp) {
                return cp.matchesCountPattern("11111") && cp.notSameSuite() && cp.areCardsOrdered();
            }
        },
        THREE_OF_A_KIND(4) {
            @Override
            public boolean match(final CardProcessor cp) {
                return cp.matchesCountPattern("113") && cp.notSameSuite() && cp.cardsUnordered();
            }
        },
        TWO_PAIRS(3) {
            @Override
            public boolean match(final CardProcessor cp) {
                return cp.matchesCountPattern("122") && cp.notSameSuite() && cp.cardsUnordered();
            }
        },
        ONE_PAIR(2) {
            @Override
            public boolean match(final CardProcessor cp) {
                return cp.matchesCountPattern("1112") && cp.notSameSuite() && cp.cardsUnordered();
            }
        },
        HIGH_CARD(1) {
            @Override
            public boolean match(final CardProcessor cp) {
                return cp.matchesCountPattern("11111") && cp.notSameSuite() && cp.cardsUnordered();
            }
        };

        private final Integer rankWeight;

        RankType(final Integer rankWeight) {
            this.rankWeight = rankWeight;
        }

        public abstract boolean match(final CardProcessor cp);

    }

}
