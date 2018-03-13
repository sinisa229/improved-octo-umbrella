package com.poker.rank;

import static java.util.Arrays.stream;

public class RankProvider {

    private final HandAnalyzer handAnalyzer;

    public RankProvider(HandAnalyzer handAnalyzer) {
        this.handAnalyzer = handAnalyzer;
    }

    public Rank getHighest() {
        final RankType rankType = stream(RankType.values()).filter(rt -> rt.match(handAnalyzer)).findFirst().orElseThrow(this::getRankMismatch);
        return new Rank(rankType, handAnalyzer.getValueWeight());
    }

    private RuntimeException getRankMismatch() {
        return new RuntimeException("No type matches handAnalyzer: " + handAnalyzer);
    }

}
