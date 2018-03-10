package com.poker.hand.rank;

import lombok.Data;

import static java.util.Comparator.comparing;

@Data
public class Rank implements Comparable<Rank> {
    private final RankType rankType;
    private final Integer rankedCards;

    @Override
    public int compareTo(Rank o) {
        return comparing(Rank::getRankType).thenComparing(Rank::getRankedCards).compare(this, o);
    }
}
