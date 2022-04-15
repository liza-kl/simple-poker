package simplepoker.rules;

import simplepoker.Card;
import simplepoker.enums.PokerHand;
import simplepoker.PokerHandService;
import simplepoker.winnerstrategy.StrategyHelperFunctions;

import java.util.Collections;
import java.util.List;

public class TwoPairs implements PokerHandRule{
    @Override
    public boolean isSatisfiedBy(List<Card> pokerHand) {
        return Collections.frequency(StrategyHelperFunctions.getPokerHandValues(pokerHand).values(), 2) == 2;
    }

    @Override
    public PokerHand returnCorrespondingRank() {
        return PokerHand.TWOPAIRS;
    }
}
