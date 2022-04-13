package simplepoker.rules;

import simplepoker.Card;
import simplepoker.PokerHandRank;

import java.util.List;

public class HighCard implements PokerHandRule{
    @Override
    public boolean isSatisfiedBy(List<Card> pokerHand) {
        return true;
    }

    @Override
    public PokerHandRank returnCorrespondingRank() {
        return PokerHandRank.HIGHCARD;
    }
}
