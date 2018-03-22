package com.poker.rank;

public enum RankType {
    ROYAL_FLUSH(10) {
        @Override
        public boolean match(final HandAnalyzer ha) {
            return ha.matchesCountPattern("11111") && ha.sameSuite() && ha.areCardsOrdered() && ha.containsAce();
        }
    },
    STRAIGHT_FLUSH(9) {
        @Override
        public boolean match(final HandAnalyzer ha) {
            return ha.matchesCountPattern("11111") && ha.sameSuite() && ha.areCardsOrdered() && ha.doesNotContainAce();
        }
    },
    FOUR_OF_A_KIND(8) {
        @Override
        public boolean match(final HandAnalyzer ha) {
            return ha.matchesCountPattern("14") && ha.notSameSuite() && ha.cardsUnordered();
        }
    },
    FULL_HOUSE(7) {
        @Override
        public boolean match(final HandAnalyzer ha) {
            return ha.matchesCountPattern("23") && ha.notSameSuite() && ha.cardsUnordered();
        }
    },
    FLUSH(6) {
        @Override
        public boolean match(final HandAnalyzer ha) {
            return ha.matchesCountPattern("11111") && ha.sameSuite() && ha.cardsUnordered();
        }
    },
    STRAIGHT(5) {
        @Override
        public boolean match(final HandAnalyzer ha) {
            return ha.matchesCountPattern("11111") && ha.notSameSuite() && ha.areCardsOrdered();
        }
    },
    THREE_OF_A_KIND(4) {
        @Override
        public boolean match(final HandAnalyzer ha) {
            return ha.matchesCountPattern("113") && ha.notSameSuite() && ha.cardsUnordered();
        }
    },
    TWO_PAIRS(3) {
        @Override
        public boolean match(final HandAnalyzer ha) {
            return ha.matchesCountPattern("122") && ha.notSameSuite() && ha.cardsUnordered();
        }
    },
    ONE_PAIR(2) {
        @Override
        public boolean match(final HandAnalyzer ha) {
            return ha.matchesCountPattern("1112") && ha.notSameSuite() && ha.cardsUnordered();
        }
    },
    HIGH_CARD(1) {
        @Override
        public boolean match(final HandAnalyzer ha) {
            return ha.matchesCountPattern("11111") && ha.notSameSuite() && ha.cardsUnordered();
        }
    };

    private final Integer rankWeight;

    RankType(final Integer rankWeight) {
        this.rankWeight = rankWeight;
    }

    public abstract boolean match(final HandAnalyzer cp);

    public Integer getRankWeight() {
        return rankWeight;
    }
}
