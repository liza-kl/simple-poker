package simplepoker.winnerstrategy;

import simplepoker.Card;
import simplepoker.PokerHandRank;
import simplepoker.PokerHandService;

import java.util.List;

import static simplepoker.PokerHandService.getPokerHandRank;

public class RegularWinner implements WinnerStrategy{
    @Override
    public Integer computeWinner(List<Card> firstHand, List<Card> secondHand) {
        PokerHandRank firstRank = getPokerHandRank(firstHand);
        PokerHandRank secondRank = getPokerHandRank(secondHand);
        if(firstRank.pokerHandRankValue > secondRank.pokerHandRankValue) {
            return 1;
        }
        else {
            return 2;
        }
    }
}
