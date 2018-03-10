package com.poker.rank;

import org.junit.Test;

import static com.poker.rank.RankType.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

public class RankTest {

    @Test
    public void shouldFirstSortByRankType() throws Exception {
        assertThat(new Rank(THREE_OF_A_KIND, 1), greaterThan(new Rank(TWO_PAIRS, 1)));
    }

    @Test
    public void shouldThenSortByRankedCards() throws Exception {
        assertThat(new Rank(ROYAL_FLUSH, 10), greaterThan(new Rank(ROYAL_FLUSH, 5)));
    }

    private Rank r(RankType onePair) {
        return new Rank(onePair, 10);
    }

}
