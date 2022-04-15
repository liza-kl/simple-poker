package simplepoker.rules;

import simplepoker.Card;
import simplepoker.enums.PokerHand;

import java.util.List;

public interface PokerHandRule {
    boolean isSatisfiedBy(List<Card> pokerHand);

    PokerHand returnCorrespondingRank();
}
