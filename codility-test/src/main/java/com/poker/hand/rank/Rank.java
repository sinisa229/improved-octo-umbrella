package com.poker.hand.rank;

import lombok.Data;

import static java.util.Comparator.comparing;

@Data
public class Rank implements Comparable<Rank> {
    private final RankType rankType;
    private final Integer getRankedCards;

    @Override
    public int compareTo(Rank o) {
        return comparing(Rank::getRankType).thenComparing(Rank::getGetRankedCards).compare(this, o);
    }
}