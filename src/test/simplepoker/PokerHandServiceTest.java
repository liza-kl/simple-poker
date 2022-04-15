package simplepoker;

import org.junit.jupiter.api.Test;
import simplepoker.enums.CardValue;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static simplepoker.PokerHandService.getPokerHand;
import static simplepoker.enums.CardSuit.*;
import static simplepoker.enums.PokerHand.*;

class PokerHandServiceTest {

    public static String getWinnerStrategy() {
        return PokerHandService.getWinnerStrategy().getClass().getSimpleName();
    }

    @Test
    void calculateRegularWinner() {
        List<Card> firstPokerHand = List.of(new Card(H, CardValue.TWO),
                new Card(C, CardValue.THREE),
                new Card(S, CardValue.SEVEN),
                new Card(S, CardValue.Q),
                new Card(H, CardValue.K));
        List<Card> secondPokerHand = List.of(new Card(H, CardValue.THREE),
                new Card(C, CardValue.THREE),
                new Card(S, CardValue.NINE),
                new Card(S, CardValue.NINE),
                new Card(H, CardValue.K));
        assertEquals(2, PokerHandService.computeWinner(firstPokerHand, secondPokerHand));
        assertEquals(HIGHCARD, getPokerHand(firstPokerHand));
        assertEquals(TWOPAIRS, getPokerHand(secondPokerHand));
        assertEquals("RegularWinnerStrategy",getWinnerStrategy());
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
        assertEquals(HIGHCARD, getPokerHand(firstPokerHand));
        assertEquals(HIGHCARD, getPokerHand(secondPokerHand));
        assertEquals("HighestCardStrategy",getWinnerStrategy());

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
        assertEquals(2, PokerHandService.computeWinner(firstPokerHand, secondPokerHand));
        assertEquals(PAIR, getPokerHand(firstPokerHand));
        assertEquals(PAIR, getPokerHand(secondPokerHand));
        assertEquals("PairStrategy",getWinnerStrategy());

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
        assertEquals(1,PokerHandService.computeWinner(firstPokerHand,secondPokerHand));
        assertEquals(TWOPAIRS, getPokerHand(firstPokerHand));
        assertEquals(TWOPAIRS, getPokerHand(secondPokerHand));
        assertEquals("TwoPairsStrategy",getWinnerStrategy());

    }

    @Test
    void calculateWinnerIfBothHaveThreeOfAKind() {
        List<Card> firstPokerHand = List.of(new Card(H, CardValue.THREE),
                new Card(C, CardValue.NINE),
                new Card(S, CardValue.NINE),
                new Card(D, CardValue.NINE),
                new Card(H, CardValue.K));
        List<Card> secondPokerHand = List.of(new Card(D, CardValue.THREE),
                new Card(S, CardValue.SIX),
                new Card(D, CardValue.A),
                new Card(H, CardValue.A),
                new Card(S, CardValue.A));
        assertEquals(   2,PokerHandService.computeWinner(firstPokerHand,secondPokerHand));
        assertEquals(THREEOFAKIND, getPokerHand(firstPokerHand));
        assertEquals(THREEOFAKIND, getPokerHand(secondPokerHand));
        assertEquals("ThreeOfAKindStrategy",getWinnerStrategy());
    }

    @Test
    void calculateWinnerIfBothHaveFourOfAKind() {
        List<Card> firstPokerHand = List.of(new Card(H, CardValue.NINE),
                new Card(C, CardValue.NINE),
                new Card(S, CardValue.NINE),
                new Card(D, CardValue.NINE),
                new Card(H, CardValue.K));
        List<Card> secondPokerHand = List.of(new Card(D, CardValue.SIX),
                new Card(S, CardValue.SIX),
                new Card(D, CardValue.SIX),
                new Card(H, CardValue.SIX),
                new Card(S, CardValue.A));
        assertEquals(1,PokerHandService.computeWinner(firstPokerHand,secondPokerHand));
        assertEquals(FOUROFAKIND, getPokerHand(firstPokerHand));
        assertEquals(FOUROFAKIND, getPokerHand(secondPokerHand));
        assertEquals("FourOfAKindStrategy",getWinnerStrategy());
    }
    @Test
    void calculateWinnerIfBothHaveFlush() {
        List<Card> firstPokerHand = List.of(new Card(H, CardValue.NINE),
                new Card(H, CardValue.SEVEN),
                new Card(H, CardValue.FIVE),
                new Card(H, CardValue.Q),
                new Card(H, CardValue.K));
        List<Card> secondPokerHand = List.of(new Card(D, CardValue.SIX),
                new Card(D, CardValue.FIVE),
                new Card(D, CardValue.TWO),
                new Card(D, CardValue.J),
                new Card(D, CardValue.A));
        assertEquals(2,PokerHandService.computeWinner(firstPokerHand,secondPokerHand));
        assertEquals(FLUSH, getPokerHand(firstPokerHand));
        assertEquals(FLUSH, getPokerHand(secondPokerHand));
        assertEquals("HighestCardStrategy",getWinnerStrategy());
    }

    @Test
    void calculateWinnerIfBothHaveFullHouse() {
        List<Card> firstPokerHand = List.of(new Card(H, CardValue.SIX),
                new Card(H, CardValue.SIX),
                new Card(H, CardValue.SIX),
                new Card(H, CardValue.K),
                new Card(H, CardValue.K));
        List<Card> secondPokerHand = List.of(new Card(D, CardValue.SEVEN),
                new Card(D, CardValue.SEVEN),
                new Card(D, CardValue.SEVEN),
                new Card(D, CardValue.J),
                new Card(D, CardValue.J));
        assertEquals(2,PokerHandService.computeWinner(firstPokerHand,secondPokerHand));
        assertEquals(FULLHOUSE, getPokerHand(firstPokerHand));
        assertEquals(FULLHOUSE, getPokerHand(secondPokerHand));
        assertEquals("ThreeOfAKindStrategy",getWinnerStrategy());
    }

    @Test
    void calculateWinnerIfBothHaveStraightFlush() {
        List<Card> firstPokerHand = List.of(new Card(H, CardValue.FIVE),
                new Card(H, CardValue.SIX),
                new Card(H, CardValue.SEVEN),
                new Card(H, CardValue.EIGHT),
                new Card(H, CardValue.NINE));
        List<Card> secondPokerHand = List.of(new Card(D, CardValue.SEVEN),
                new Card(D, CardValue.EIGHT),
                new Card(D, CardValue.NINE),
                new Card(D, CardValue.TEN),
                new Card(D, CardValue.J));
        assertEquals(2,PokerHandService.computeWinner(firstPokerHand,secondPokerHand));
        assertEquals(STRAIGHT, getPokerHand(firstPokerHand));
        assertEquals(STRAIGHT, getPokerHand(secondPokerHand));
        assertEquals("StraightStrategy",getWinnerStrategy());
    }
}
