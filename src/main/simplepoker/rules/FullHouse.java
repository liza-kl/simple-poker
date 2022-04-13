package simplepoker.rules;

import simplepoker.Card;
import simplepoker.PokerHandRank;

import java.util.List;

public class FullHouse implements PokerHandRule{
    @Override
    public boolean isSatisfiedBy(List<Card> pokerHand) {
        ThreeOfAKind threeOfAKindRule = new ThreeOfAKind();
        Pair pairRule = new Pair();
        return threeOfAKindRule.isSatisfiedBy(pokerHand) && pairRule.isSatisfiedBy(pokerHand);
    }

    @Override
    public PokerHandRank returnCorrespondingRank() {
        return PokerHandRank.FULLHOUSE;
    }
}
