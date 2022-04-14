package simplepoker.winnerstrategy;

import simplepoker.Card;

import java.util.List;

public interface WinnerStrategy {
    public Integer computeWinner(List<Card> firstHand, List<Card> secondHand);
}
