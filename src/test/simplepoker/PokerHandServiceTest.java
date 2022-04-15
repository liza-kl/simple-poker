package simplepoker;

import org.junit.jupiter.api.Test;
import simplepoker.enums.CardValue;
import simplepoker.winnerstrategy.PairStrategy;
import simplepoker.winnerstrategy.TwoPairsStrategy;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static simplepoker.enums.CardSuit.*;

class PokerHandServiceTest {

    @Test
    void calculateWinnerByValueIfNoOneHasAPokerHand() {
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
        assertEquals(2, PokerHandService.computeWinner(firstPokerHand, secondPokerHand));
    }

    @Test
    void calculateIfBothHaveHighestCardWinner() {
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
        assertEquals(1, PokerHandService.computeWinner(firstPokerHand, secondPokerHand));
    }

    @Test
    void calculateIfBothHavePairWinner() {
        List<Card> firstPokerHand = List.of(new Card(H, CardValue.THREE),
                new Card(C, CardValue.THREE),
                new Card(S, CardValue.SEVEN),
                new Card(S, CardValue.Q),
                new Card(H, CardValue.K));
        List<Card> secondPokerHand = List.of(new Card(D, CardValue.THREE),
                new Card(S, CardValue.THREE),
                new Card(D, CardValue.SEVEN),
                new Card(H, CardValue.J),
                new Card(S, CardValue.A));
        PairStrategy pairStrategy = new PairStrategy();
        assertEquals(2, pairStrategy.computeWinner(firstPokerHand, secondPokerHand));
    }

    @Test
    void calculateIfBothHaveTwoPairsWinner() {
        List<Card> firstPokerHand = List.of(new Card(H, CardValue.THREE),
                new Card(C, CardValue.THREE),
                new Card(S, CardValue.NINE),
                new Card(S, CardValue.NINE),
                new Card(H, CardValue.K));
        List<Card> secondPokerHand = List.of(new Card(D, CardValue.THREE),
                new Card(S, CardValue.THREE),
                new Card(D, CardValue.EIGHT),
                new Card(H, CardValue.EIGHT),
                new Card(S, CardValue.A));
        TwoPairsStrategy twoPairsStrategy = new TwoPairsStrategy();
        System.out.println(twoPairsStrategy.computeWinner(firstPokerHand,secondPokerHand));
    }
}
