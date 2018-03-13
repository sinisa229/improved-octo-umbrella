package com.poker;

import com.poker.file.FileReader;
import com.poker.hand.Hand;
import com.poker.rank.Rank;
import com.poker.rank.RankProvider;
import com.poker.rank.analysis.HandAnalyzerImpl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class Game {

    public static void main(String[] args) throws IOException, URISyntaxException {
        final Game game = new Game();
        new FileReader("poker.txt").readFile(game::getWinner);
    }

    protected int getWinner(String s) {
        final List<String> handStrings = new CardDealer().deal(s);
        Rank hand1 = getRank(handStrings.get(0));
        Rank hand2 = getRank(handStrings.get(1));
        if (hand1.equals(hand2)) {
            throw new RuntimeException("Equal Hands not allowed");
        }
        int i = hand1.compareTo(hand2);
        System.out.println("Comparing " + hand1 + " to " + hand2 + " And winner is " + (i > 0?" Player 1":" Player 2"));
        return i;
    }

    private Rank getRank(final String handString) {
        Hand hand = new Hand(handString);
        return new RankProvider(new HandAnalyzerImpl(hand)).getHighest();
    }

}
