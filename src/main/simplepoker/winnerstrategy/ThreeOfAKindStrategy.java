package simplepoker.winnerstrategy;

import simplepoker.Card;
import simplepoker.enums.CardValue;
import simplepoker.PokerHandService;

import java.util.*;

import static simplepoker.winnerstrategy.StrategyHelperFunctions.getKeyByValue;


public class ThreeOfAKindStrategy implements WinnerStrategy{

    @Override
    public Integer computeWinner(List<Card> firstHand, List<Card> secondHand) {
        CardValue getValueOfThreeKindFirstHand = getKeyByValue(PokerHandService.getPokerHandValues(firstHand), 3);
        CardValue getValueOfThreeKindSecondHand = getKeyByValue(PokerHandService.getPokerHandValues(secondHand), 3);
        assert getValueOfThreeKindFirstHand != null;
        assert getValueOfThreeKindSecondHand != null;
        return (getValueOfThreeKindFirstHand.value > getValueOfThreeKindSecondHand.value) ? 1 : 2;
    }
}
