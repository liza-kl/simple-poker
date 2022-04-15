package simplepoker.rules;

import simplepoker.Card;
import simplepoker.enums.PokerHand;
import simplepoker.winnerstrategy.StrategyHelperFunctions;

import java.util.Collections;
import java.util.List;

public interface PokerHandRule {
    boolean isSatisfiedBy(List<Card> pokerHand);


    private boolean checkIfNOfACardValueExists(List<Card> pokerHand, int nTimesOfCardValue) {
        return Collections.frequency(StrategyHelperFunctions.getPokerHandValues(pokerHand).values(), nTimesOfCardValue) == 1;
    }

    PokerHand returnCorrespondingRank();
}
