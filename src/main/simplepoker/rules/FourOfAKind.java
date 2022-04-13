package simplepoker.rules;

import simplepoker.Card;
import simplepoker.PokerHandRank;
import simplepoker.PokerHandService;

import java.util.Collections;
import java.util.List;

public class FourOfAKind implements PokerHandRule{
    @Override
    public boolean isSatisfiedBy(List<Card> pokerHand) {
        return Collections.frequency(PokerHandService.getPokerHandValues(pokerHand).values(), 4) == 1;
    }

    @Override
    public PokerHandRank returnCorrespondingRank() {
        return PokerHandRank.FOUROFAKIND;
    }
}
