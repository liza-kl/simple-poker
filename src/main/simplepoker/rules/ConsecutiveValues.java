package simplepoker.rules;

import simplepoker.Card;
import simplepoker.enums.CardValue;
import simplepoker.enums.PokerHand;
import simplepoker.winnerstrategy.StrategyHelperFunctions;

import java.util.*;

public class ConsecutiveValues implements PokerHandRule{
    @Override
    public boolean isSatisfiedBy(List<Card> pokerHand) {
        Set<CardValue> pokerHandCardValues = StrategyHelperFunctions.getPokerHandValues(pokerHand).keySet();
        ArrayList<Integer> pokerHandIntegerValues = new ArrayList<>();
        pokerHandCardValues.forEach(it -> pokerHandIntegerValues.add(it.getCardValue()));
        Collections.sort(pokerHandIntegerValues);

        for (int i = 0; i < pokerHandIntegerValues.size(); i++) {
            if (i + 1 == pokerHandIntegerValues.size()) {
                break;
            }
            if (pokerHandIntegerValues.get(i + 1) - pokerHandIntegerValues.get(i) != 1) {
                return false;
            }
        }
        return true;
    }

    @Override
    public PokerHand returnCorrespondingRank() {
        throw new UnsupportedOperationException("There is no corresponding PokerHand to this rule");
    }
}
