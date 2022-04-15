package simplepoker.rules;

import simplepoker.Card;
import simplepoker.enums.PokerHand;

import java.util.List;

public interface PokerHandRule {
    public boolean isSatisfiedBy(List<Card> pokerHand);

    public PokerHand returnCorrespondingRank();
}
