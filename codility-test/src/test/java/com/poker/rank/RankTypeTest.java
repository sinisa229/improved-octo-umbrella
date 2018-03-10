package com.poker.rank;

import org.junit.Test;

import static com.poker.rank.RankType.*;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

public class RankTypeTest {

    @Test
    public void rankTypesOrderedByHighestFirst() throws Exception {
        assertThat(asList(RankType.values()), contains(ROYAL_FLUSH, STRAIGHT_FLUSH, FOUR_OF_A_KIND, FULL_HOUSE, FLUSH, STRAIGHT, THREE_OF_A_KIND, TWO_PAIRS, ONE_PAIR, HIGH_CARD));
    }

}