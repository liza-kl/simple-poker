package simplepoker.rules;

import simplepoker.Card;
import simplepoker.PokerHandRank;

import java.util.List;

public interface PokerHandRule {
    public boolean isSatisfiedBy(List<Card> pokerHand);

    public PokerHandRank returnCorrespondingRank();
}
