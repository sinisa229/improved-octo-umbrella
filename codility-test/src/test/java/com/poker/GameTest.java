package com.poker;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void shouldDeclareWinner() throws Exception {
        assertThat(new Game().getWinner("3S 4S 5S 6S 7S 2S 3S 4S 5S 6S"), equalTo(1));
        assertThat(new Game().getWinner("3S 4S 5S 6S 7C 2C 2S 2S 5S 6C"), equalTo(1));
        assertThat(new Game().getWinner("JS 4S 5S 6S 7S 2C 2S 2S 5S 6C"), equalTo(1));
    }

}