package simplepoker.rules;

import simplepoker.Card;
import simplepoker.enums.PokerHand;
import simplepoker.PokerHandService;
import simplepoker.winnerstrategy.StrategyHelperFunctions;

import java.util.Collections;
import java.util.List;

import static simplepoker.enums.PokerHand.PAIR;

public class Pair implements PokerHandRule{
    @Override
    public boolean isSatisfiedBy(List<Card> pokerHand) {
        return Collections.frequency(StrategyHelperFunctions.getPokerHandValues(pokerHand).values(), 2) == 1;
    }

    @Override
    public PokerHand returnCorrespondingRank() {
        return PAIR;
    }
}
