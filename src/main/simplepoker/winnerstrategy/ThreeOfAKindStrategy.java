package simplepoker.winnerstrategy;

import simplepoker.Card;
import simplepoker.enums.CardValue;

import java.util.*;

import static simplepoker.winnerstrategy.StrategyHelperFunctions.getKeyByValue;


public class ThreeOfAKindStrategy implements WinnerStrategy{

    @Override
    public Integer computeWinner(List<Card> firstHand, List<Card> secondHand) {
        CardValue getValueOfThreeKindFirstHand = getKeyByValue(StrategyHelperFunctions.getPokerHandValues(firstHand), 3);
        CardValue getValueOfThreeKindSecondHand = getKeyByValue(StrategyHelperFunctions.getPokerHandValues(secondHand), 3);
        assert getValueOfThreeKindFirstHand != null;
        assert getValueOfThreeKindSecondHand != null;
        return (getValueOfThreeKindFirstHand.getCardValue() > getValueOfThreeKindSecondHand.getCardValue()) ? 1 : 2;
    }
}
