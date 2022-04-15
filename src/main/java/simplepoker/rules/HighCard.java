package simplepoker.rules;

import simplepoker.Card;
import simplepoker.enums.PokerHand;

import java.util.List;

public class HighCard implements PokerHandRule{
    @Override
    public boolean isSatisfiedBy(List<Card> pokerHand) {
        return true;
    }

    @Override
    public PokerHand returnCorrespondingRank() {
        return PokerHand.HIGHCARD;
    }
}
