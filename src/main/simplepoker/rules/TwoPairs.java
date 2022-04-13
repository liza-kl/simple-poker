package simplepoker.rules;

import simplepoker.Card;
import simplepoker.PokerHandRank;
import simplepoker.PokerHandService;

import java.util.Collections;
import java.util.List;

public class TwoPairs implements PokerHandRule{
    @Override
    public boolean isSatisfiedBy(List<Card> pokerHand) {
        return Collections.frequency(PokerHandService.getPokerHandValues(pokerHand).values(), 2) == 2;
    }

    @Override
    public PokerHandRank returnCorrespondingRank() {
        return PokerHandRank.TWOPAIRS;
    }
}
