package simplepoker.rules;

import simplepoker.Card;
import simplepoker.enums.PokerHand;
import simplepoker.PokerHandService;

import java.util.List;

public class Flush implements PokerHandRule{
    @Override
    public boolean isSatisfiedBy(List<Card> pokerHand) {
        return PokerHandService.getPokerHandSuitValues(pokerHand).size() == 1;
    }

    @Override
    public PokerHand returnCorrespondingRank() {
        return PokerHand.FLUSH;
    }
}
