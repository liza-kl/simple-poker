package simplepoker.winnerstrategy;

import simplepoker.Card;
import simplepoker.enums.PokerHand;

import java.util.List;

import static simplepoker.PokerHandService.getPokerHand;

public class RegularWinnerStrategy implements WinnerStrategy {
    @Override
    public Integer computeWinner(List<Card> firstHand, List<Card> secondHand) {
        PokerHand firstRank = getPokerHand(firstHand);
        PokerHand secondRank = getPokerHand(secondHand);
        return (firstRank.pokerHandRankValue > secondRank.pokerHandRankValue) ? 1 : 2;
    }
}
