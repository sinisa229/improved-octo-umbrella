package com.poker.rank.analysis;

import com.poker.hand.Hand;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HandAnalyzerImplTest {

    @Test
    public void shouldMatchCountPattern() throws Exception {
        HandAnalyzerImpl handAnalyzerImpl = new HandAnalyzerImpl(new Hand("AS 2S AC"));
        assertTrue(handAnalyzerImpl.matchesCountPattern("12"));
    }

    @Test
    public void shouldMatchFourOfAKindPattern() throws Exception {
        HandAnalyzerImpl handAnalyzerImpl = new HandAnalyzerImpl(new Hand("AS AS AS 2S AC"));
        assertTrue(handAnalyzerImpl.matchesCountPattern("14"));
    }

    @Test
    public void shouldMatchFullHousePattern() throws Exception {
        HandAnalyzerImpl handAnalyzerImpl = new HandAnalyzerImpl(new Hand("AS 2S AS 2S AC"));
        assertTrue(handAnalyzerImpl.matchesCountPattern("23"));
    }

    @Test
    public void shouldMatchSameSuite() throws Exception {
        Hand hand = new Hand("AS KS 2S");
        assertTrue(new HandAnalyzerImpl(hand).sameSuite());
    }

    @Test
    public void shouldNotMatchSameSuite() throws Exception {
        Hand hand = new Hand("AS KH 2S");
        assertFalse(new HandAnalyzerImpl(hand).sameSuite());
    }

    @Test
    public void shouldMatchOrdered() throws Exception {
        assertTrue(new HandAnalyzerImpl(new Hand("2S 3S 4S")).areCardsOrdered());
    }

    @Test
    public void shouldNotMatchOrderedWhenDuplicatedValue() throws Exception {
        assertFalse(new HandAnalyzerImpl(new Hand("2S 2S 4S")).areCardsOrdered());
    }

    @Test
    public void shouldNotMatchOrderedWhenMissingValue() throws Exception {
        assertFalse(new HandAnalyzerImpl(new Hand("2S 5S 4S")).areCardsOrdered());
    }

}