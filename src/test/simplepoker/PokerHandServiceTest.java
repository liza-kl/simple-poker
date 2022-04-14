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
                new Card(C, CardValue.TEN),
                new Card(D, CardValue.TEN),
                new Card(S, CardValue.FIVE));
        PokerHandService pokerHandService = new PokerHandService();
        //assertTrue(new Flush().isSatisfiedBy(pokerHand));
        assertEquals(pokerHandService.getPokerHandRank(pokerHand), "Flush");
    }

    @Test
    void consecutivePokerHand() {
        List<Card> pokerHand = List.of(new Card(S, CardValue.SIX),
                new Card(S, CardValue.NINE),
                new Card(S, CardValue.FIVE),
                new Card(D, CardValue.EIGHT),
                new Card(S, CardValue.SEVEN));
        PokerHandService pokerHandService = new PokerHandService();
    }

    @Test
    void calculateRegularWinner() {
        List<Card> firstPokerHand = List.of(new Card(H, CardValue.TWO),
                new Card(C, CardValue.THREE),
                new Card(S, CardValue.FOUR),
                new Card(S, CardValue.FIVE),
                new Card(H, CardValue.SIX));
        List<Card> secondPokerHand = List.of(new Card(D, CardValue.TWO),
                new Card(H, CardValue.SEVEN),
                new Card(D, CardValue.SEVEN),
                new Card(H, CardValue.J),
                new Card(S, CardValue.K));
        assertEquals(PokerHandService.computeWinner(firstPokerHand, secondPokerHand), 2);
    }
    @Test
    void calculateHighestCardWinner() {
        List<Card> firstPokerHand = List.of(new Card(H, CardValue.TWO),
                new Card(C, CardValue.THREE),
                new Card(S, CardValue.SEVEN),
                new Card(S, CardValue.Q),
                new Card(H, CardValue.K));
        List<Card> secondPokerHand = List.of(new Card(D, CardValue.TWO),
                new Card(H, CardValue.THREE),
                new Card(D, CardValue.SEVEN),
                new Card(H, CardValue.J),
                new Card(S, CardValue.K));
        assertEquals(PokerHandService.computeWinner(firstPokerHand, secondPokerHand), 1);
    }


}
