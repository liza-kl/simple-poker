package simplepoker;

import simplepoker.enums.CardValue;

import java.util.List;

import static simplepoker.enums.CardSuit.*;
import static simplepoker.enums.CardSuit.S;

public class Main {

    public static void main(String[] args) {
        List<Card> firstPokerHand = List.of(
                new Card(H, CardValue.THREE),
                new Card(C, CardValue.THREE),
                new Card(S, CardValue.EIGHT),
                new Card(S, CardValue.EIGHT),
                new Card(H, CardValue.A));
        List<Card> secondPokerHand = List.of(
                new Card(D, CardValue.THREE),
                new Card(S, CardValue.THREE),
                new Card(D, CardValue.EIGHT),
                new Card(H, CardValue.EIGHT),
                new Card(S, CardValue.A));

        Integer winner = PokerHandService.computeWinner(firstPokerHand, secondPokerHand);
        System.out.println("The winner is PokerHand No." + winner);
    }
}
