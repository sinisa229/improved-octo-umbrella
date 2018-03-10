package com.poker.hand.rank;

import java.util.Optional;

import static java.util.Arrays.stream;

public class RankProvider {

    private final HandAnalyzer handAnalyzer;

    public RankProvider(HandAnalyzer handAnalyzer) {
        this.handAnalyzer = handAnalyzer;
    }

    public Rank getHighestRank() {
        final Optional<RankType> rankType = stream(RankType.values()).filter(rt -> rt.match(handAnalyzer)).findFirst();
        return new Rank(rankType.orElseThrow(() -> new RuntimeException("No type matches handAnalyzer: " + handAnalyzer)), handAnalyzer.getValuePatternAsInt());
    }

}
