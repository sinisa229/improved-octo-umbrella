package com.poker;

import com.poker.hand.Hand;
import com.poker.rank.Rank;
import com.poker.rank.RankProvider;
import com.poker.rank.analysis.HandAnalyzerImpl;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class Game {

    public int getWinner(String s) {
        List<String> strings = asList(s.split("\\s"));
        Rank hand = getRank(strings.subList(0, 5));
        Rank hand2 = getRank(strings.subList(5, 10));
        System.out.println("Comparing " + hand + " to " + hand2);
        return hand.compareTo(hand2);

    }

    private Rank getRank(List<String> strings) {
        Hand hand = new Hand(strings.stream().collect(Collectors.joining(" ")));
        return new RankProvider(new HandAnalyzerImpl(hand)).getHighestRank();
    }

}
