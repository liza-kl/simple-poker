package simplepoker.rules;

import simplepoker.Card;
import simplepoker.enums.PokerHand;
import simplepoker.winnerstrategy.StrategyHelperFunctions;

import java.util.Collections;
import java.util.List;

public class ThreeOfAKind implements PokerHandRule{

    private boolean checkIfThreeOfACardValueExists(List<Card> pokerHand) {
        return Collections.frequency(StrategyHelperFunctions.getPokerHandValues(pokerHand).values(), 3) == 1;
    }

    @Override
    public boolean isSatisfiedBy(List<Card> pokerHand) {
        return checkIfThreeOfACardValueExists(pokerHand);
    }

    @Override
    public PokerHand returnCorrespondingRank() {
        return PokerHand.THREEOFAKIND;
    }
}
