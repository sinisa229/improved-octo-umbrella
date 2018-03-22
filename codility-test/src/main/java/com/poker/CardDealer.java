package com.poker;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class CardDealer {

    public static final int CARDS_PER_PLAYER = 5;

    /**
     * @param s Space separated string of card representations. Example "2S 3H 4C 5D 6S 7H 8C 9D TS JH QC KD AS"
     */
    public List<String> deal(String s) {
        List<String> cards = asList(s.split("\\s"));
        System.out.println("Cards: " + s);
        return IntStream.range(0, getNumberOfPlayers(cards)).mapToObj(player -> getHandString(cards, player)).collect(toList());
    }

    private String getHandString(final List<String> cards, final int player) {
        return cards.subList(player * CARDS_PER_PLAYER, (player + 1) * 5).stream().collect(Collectors.joining(" "));
    }

    private int getNumberOfPlayers(final List<String> cards) {
        return cards.size()/CARDS_PER_PLAYER;
    }

}
