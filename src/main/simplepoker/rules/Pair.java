package simplepoker.rules;

import simplepoker.Card;
import simplepoker.PokerHandRank;
import simplepoker.PokerHandService;

import java.util.Collections;
import java.util.List;

import static simplepoker.PokerHandRank.PAIR;

public class Pair implements PokerHandRule{
    @Override
    public boolean isSatisfiedBy(List<Card> pokerHand) {
        return Collections.frequency(PokerHandService.getPokerHandValues(pokerHand).values(), 2) == 1;
    }

    @Override
    public PokerHandRank returnCorrespondingRank() {
        return PAIR;
    }
}
