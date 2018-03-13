package com.poker.rank;

import lombok.Data;

import static java.util.Comparator.comparing;

@Data
public class Rank implements Comparable<Rank> {
    private final RankType rankType;
    private final Integer rankedCards;

    @Override
    public int compareTo(Rank o) {
        return comparing(Rank::getRankWeight).thenComparing(Rank::getRankedCards).compare(this, o);
    }

    private Integer getRankWeight() {
        return getRankType().getRankWeight();
    }

}
