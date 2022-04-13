package simplepoker.rules;

import simplepoker.Card;
import simplepoker.CardSuit;
import simplepoker.PokerHandRank;
import simplepoker.PokerHandService;

import java.util.HashMap;
import java.util.List;

public class Flush implements PokerHandRule{
    @Override
    public boolean isSatisfiedBy(List<Card> pokerHand) {
        return PokerHandService.getPokerHandSuitValues(pokerHand).size() == 1;
    }

    @Override
    public PokerHandRank returnCorrespondingRank() {
        return PokerHandRank.FLUSH;
    }
}
