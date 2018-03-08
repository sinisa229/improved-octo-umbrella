package com.poker;

public class RankTest {

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

    interface CardProcessor {
        String getCountsPattern();
        Boolean sameSuite();
        Boolean notSameSuite();
        Boolean cardsOrdered();
    }

    private class RankFactory {

        private final CardProcessor cardProcessor;

        public RankFactory(CardProcessor cardProcessor) {
            this.cardProcessor = cardProcessor;
        }

        public Rank getHighestRank() {
            if (countPattern("11111") && sameSuite() && cardsOrdered()) {

            }
        }

        public String getCountsPattern() {
            return cardProcessor.getCountsPattern();
        }

        public Boolean sameSuite() {
            return cardProcessor.sameSuite();
        }

        public Boolean notSameSuite() {
            return cardProcessor.notSameSuite();
        }

        public Boolean cardsOrdered() {
            return cardProcessor.cardsOrdered();
        }

        private boolean countPattern(final String anObject) {
            return cardProcessor.getCountsPattern().equals(anObject);
        }

    }

    interface Rank {
        Integer getRankWeight();
    }

}
