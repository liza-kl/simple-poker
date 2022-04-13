package simplepoker.rules;

import simplepoker.Card;
import simplepoker.PokerHandRank;

import java.util.List;

public class StraightFlush implements PokerHandRule {
    @Override
    public boolean isSatisfiedBy(List<Card> pokerHand) {
        Flush flushRule = new Flush();
        ConsecutiveValues consecutiveValuesRules = new ConsecutiveValues();
        return flushRule.isSatisfiedBy(pokerHand) && consecutiveValuesRules.isSatisfiedBy(pokerHand);
    }

    @Override
    public PokerHandRank returnCorrespondingRank() {
        return PokerHandRank.STRAIGHT;
    }
}
