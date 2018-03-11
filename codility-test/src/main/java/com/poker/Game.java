package com.poker;

import com.poker.hand.Hand;
import com.poker.rank.Rank;
import com.poker.rank.RankProvider;
import com.poker.rank.analysis.HandAnalyzerImpl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

public class Game {

    public static void main(String[] args) throws IOException, URISyntaxException {
        new Game().readFile();
    }

    private void readFile() throws URISyntaxException, IOException {
        Path path = Paths.get(getClass().getClassLoader()
                .getResource("poker.txt").toURI());

        try(Stream<String> lines = Files.lines(path)) {
            lines.forEach(this::getWinner);
        }
    }

    public int getWinner(String s) {
        List<String> strings = asList(s.split("\\s"));
        System.out.println("Cards: " + s);
        Rank hand = getRank(strings.subList(0, 5));
        Rank hand2 = getRank(strings.subList(5, 10));
        int i = hand.compareTo(hand2);
        if (i == 0) {
            throw new RuntimeException("Equal Hands not allowed");
        }
        System.out.println("Comparing " + hand + " to " + hand2 + " And winner is " + (i > 0?" Player 1":" Player 2"));
        return i;

    }

    private Rank getRank(List<String> strings) {
        Hand hand = new Hand(strings.stream().collect(Collectors.joining(" ")));
        return new RankProvider(new HandAnalyzerImpl(hand)).getHighestRank();
    }

}
