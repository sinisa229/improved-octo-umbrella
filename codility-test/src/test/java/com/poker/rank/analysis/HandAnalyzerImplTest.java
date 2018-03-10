package com.poker.rank.analysis;

import com.poker.hand.Hand;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class HandAnalyzerImplTest {

    @Test
    public void shouldMatchCountPattern() throws Exception {
        assertTrue(ha("AS 2S AC").matchesCountPattern("12"));
    }

    @Test
    public void shouldMatchFourOfAKindPattern() throws Exception {
        assertTrue(ha("AS AS AS 2S AC").matchesCountPattern("14"));
    }

    @Test
    public void shouldMatchFullHousePattern() throws Exception {
        assertTrue(ha("AS 2S AS 2S AC").matchesCountPattern("23"));
    }

    @Test
    public void shouldMatchSameSuite() throws Exception {
        assertTrue(ha("AS KS 2S").sameSuite());
    }

    @Test
    public void shouldNotMatchSameSuite() throws Exception {
        assertFalse(ha("AS KH 2S").sameSuite());
    }

    @Test
    public void shouldMatchOrdered() throws Exception {
        assertTrue(ha("2S 3S 4S").areCardsOrdered());
    }

    @Test
    public void shouldNotMatchOrderedWhenDuplicatedValue() throws Exception {
        assertFalse(ha("2S 2S 4S").areCardsOrdered());
    }

    @Test
    public void shouldNotMatchOrderedWhenMissingValue() throws Exception {
        assertFalse(ha("2S 5S 4S").areCardsOrdered());
    }

    @Test
    public void shouldRecognizeAce() throws Exception {
        assertTrue(ha("AS").containsAce());
    }

    @Test
    public void shouldNotRecognizeAce() throws Exception {
        assertFalse(ha("2S").containsAce());
    }

    @Test
    public void shouldCalculateValueWeightBasedOnDecimalNotation() throws Exception {
        assertThat(ha("2S 2S 2S AS AS").getValueWeight(), equalTo((14 + 2*10)));
        assertThat(ha("AS AS AS 2S 2S").getValueWeight(), equalTo((2 + 14*10)));
        assertThat(ha("2S 3S 4S 5S 6S").getValueWeight(), equalTo((2 + 3*10 + 4*100 + 5*1000 + 6*10000)));
    }

    @Test
    public void shouldCalculateValueWeightThatComparesCorrectly() throws Exception {
        testGreaterValueWeight("AS AS 2S 2S 4S", "AS AS 2S 2S 3S");
        testGreaterValueWeight("AS AS 2S 2S 4S", "KS KS 2S 2S 4S");
        testGreaterValueWeight("AS AS 3S 3S 4S", "AS AS 2S 2S 4S");
        testGreaterValueWeight("5S AS AS 4S 6S", "AS AS 5S 4S 3S");
        testGreaterValueWeight("3S 4S 5S 6S 7S", "2S 3S 4S 5S 6S");
        testGreaterValueWeight("TS JS QS KS AS", "9S TS JS QS KS");
    }

    private HandAnalyzerImpl ha(String hand) {
        return new HandAnalyzerImpl(new Hand(hand));
    }

    private void testGreaterValueWeight(String greater, String smaller) {
        assertThat(ha(greater).getValueWeight(), greaterThan(ha(smaller).getValueWeight()));
    }

}