package simplepoker;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static simplepoker.CardSuit.*;

class PokerHandServiceTest {

    @Test
    void getPokerHandValues() {
        List<Card> pokerHand = List.of(new Card(S, CardValue.A),
                new Card(S, CardValue.TEN),
                new Card(S, CardValue.NINE),
                new Card(D, CardValue.EIGHT),
                new Card(S, CardValue.FIVE));
        PokerHandService pokerHandService = new PokerHandService(pokerHand);
        assertFalse(pokerHandService.checkIfPair());
        assertFalse(pokerHandService.checkIfTwoPairs());
        assertTrue(pokerHandService.checkIfThreeOfAKind());
        pokerHandService.checkIfStraightFlush();
        assertFalse(pokerHandService.checkIfFlush());
    }

    @Test
    void consecutivePokerHand() {
        List<Card> pokerHand = List.of(new Card(S, CardValue.SIX),
                new Card(S, CardValue.NINE),
                new Card(S, CardValue.FIVE),
                new Card(D, CardValue.EIGHT),
                new Card(S, CardValue.SEVEN));
        PokerHandService pokerHandService = new PokerHandService(pokerHand);
        assertTrue(pokerHandService.checkIfConsecutiveValues());
    }

}
