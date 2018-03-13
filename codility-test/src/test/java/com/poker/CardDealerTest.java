package com.poker;

import org.junit.Test;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.*;

public class CardDealerTest {

    @Test
    public void shouldDealTwoPlayersFiveCardsEach() throws Exception {
        assertThat(new CardDealer().deal("QD AS 6H JS 2C 3D 9H KC 4H 8S"), contains("QD AS 6H JS 2C", "3D 9H KC 4H 8S"));
    }
}