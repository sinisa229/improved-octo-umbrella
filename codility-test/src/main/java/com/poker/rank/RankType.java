package com.poker.rank;

public enum RankType {
    ROYAL_FLUSH(10) {
        @Override
        public boolean match(final HandAnalyzer cp) {
            return cp.matchesCountPattern("11111") && cp.sameSuite() && cp.areCardsOrdered() && cp.containsAce();
        }
    },
    STRAIGHT_FLUSH(9) {
        @Override
        public boolean match(final HandAnalyzer cp) {
            return cp.matchesCountPattern("11111") && cp.sameSuite() && cp.areCardsOrdered() && cp.doesNotContainAce();
        }
    },
    FOUR_OF_A_KIND(8) {
        @Override
        public boolean match(final HandAnalyzer cp) {
            return cp.matchesCountPattern("14") && cp.notSameSuite() && cp.cardsUnordered();
        }
    },
    FULL_HOUSE(7) {
        @Override
        public boolean match(final HandAnalyzer cp) {
            return cp.matchesCountPattern("23") && cp.notSameSuite() && cp.cardsUnordered();
        }
    },
    FLUSH(6) {
        @Override
        public boolean match(final HandAnalyzer cp) {
            return cp.matchesCountPattern("11111") && cp.sameSuite() && cp.cardsUnordered();
        }
    },
    STRAIGHT(5) {
        @Override
        public boolean match(final HandAnalyzer cp) {
            return cp.matchesCountPattern("11111") && cp.notSameSuite() && cp.areCardsOrdered();
        }
    },
    THREE_OF_A_KIND(4) {
        @Override
        public boolean match(final HandAnalyzer cp) {
            return cp.matchesCountPattern("113") && cp.notSameSuite() && cp.cardsUnordered();
        }
    },
    TWO_PAIRS(3) {
        @Override
        public boolean match(final HandAnalyzer cp) {
            return cp.matchesCountPattern("122") && cp.notSameSuite() && cp.cardsUnordered();
        }
    },
    ONE_PAIR(2) {
        @Override
        public boolean match(final HandAnalyzer cp) {
            return cp.matchesCountPattern("1112") && cp.notSameSuite() && cp.cardsUnordered();
        }
    },
    HIGH_CARD(1) {
        @Override
        public boolean match(final HandAnalyzer cp) {
            return cp.matchesCountPattern("11111") && cp.notSameSuite() && cp.cardsUnordered();
        }
    };

    private final Integer rankWeight;

    RankType(final Integer rankWeight) {
        this.rankWeight = rankWeight;
    }

    public abstract boolean match(final HandAnalyzer cp);

}
