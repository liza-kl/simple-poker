package simplepoker.rules;

import simplepoker.Card;
import simplepoker.enums.PokerHand;

import java.util.List;

public class StraightFlush implements PokerHandRule {
    @Override
    public boolean isSatisfiedBy(List<Card> pokerHand) {
        Flush flushRule = new Flush();
        ConsecutiveValues consecutiveValuesRules = new ConsecutiveValues();
        return flushRule.isSatisfiedBy(pokerHand) && consecutiveValuesRules.isSatisfiedBy(pokerHand);
    }

    @Override
    public PokerHand returnCorrespondingRank() {
        return PokerHand.STRAIGHT;
    }
}
