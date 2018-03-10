package com.poker.rank;

import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static com.poker.rank.RankType.*;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.greaterThan;

public class RankTest {

    @Test
    public void shouldFirstSortByRankType() throws Exception {
        List<Rank> ranks = asList(r(HIGH_CARD), r(ONE_PAIR), r(TWO_PAIRS), r(THREE_OF_A_KIND), r(STRAIGHT), r(FLUSH), r(FULL_HOUSE), r(FOUR_OF_A_KIND), r(STRAIGHT_FLUSH), r(ROYAL_FLUSH));
        Collections.sort(ranks);
        assertThat(ranks, contains(r(ROYAL_FLUSH), r(STRAIGHT_FLUSH), r(FOUR_OF_A_KIND), r(FULL_HOUSE), r(FLUSH), r(STRAIGHT), r(THREE_OF_A_KIND), r(TWO_PAIRS), r(ONE_PAIR), r(HIGH_CARD)));
    }

    @Test
    public void shouldThenSortByRankedCards() throws Exception {
        assertThat(new Rank(ROYAL_FLUSH, 10), greaterThan(new Rank(ROYAL_FLUSH, 5)));
    }

    private Rank r(RankType onePair) {
        return new Rank(onePair, 10);
    }

}
