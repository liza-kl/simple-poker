package simplepoker.rules;

import simplepoker.Card;
import simplepoker.enums.PokerHand;
import simplepoker.winnerstrategy.StrategyHelperFunctions;

import java.util.Collections;
import java.util.List;

public class FourOfAKind implements PokerHandRule{
    @Override
    public boolean isSatisfiedBy(List<Card> pokerHand) {
        return checkIfFourOfACardValueExists(pokerHand);
    }

    private boolean checkIfFourOfACardValueExists(List<Card> pokerHand) {
        return Collections.frequency(StrategyHelperFunctions.getPokerHandValues(pokerHand).values(), 4) == 1;
    }

    @Override
    public PokerHand returnCorrespondingRank() {
        return PokerHand.FOUROFAKIND;
    }
}
