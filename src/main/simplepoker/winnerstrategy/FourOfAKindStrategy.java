package simplepoker.winnerstrategy;

import simplepoker.Card;
import simplepoker.enums.CardValue;
import simplepoker.PokerHandService;

import java.util.List;
import static simplepoker.winnerstrategy.StrategyHelperFunctions.getKeyByValue;

public class FourOfAKindStrategy implements WinnerStrategy{
    @Override
    public Integer computeWinner(List<Card> firstHand, List<Card> secondHand) {
        CardValue getValueOfFourOfAKindFirstHand = getKeyByValue(StrategyHelperFunctions.getPokerHandValues(firstHand), 4);
        CardValue getValueOfFourOfAKindSecondHand = getKeyByValue(StrategyHelperFunctions.getPokerHandValues(secondHand), 4);
        assert getValueOfFourOfAKindFirstHand != null;
        assert getValueOfFourOfAKindSecondHand != null;
        return (getValueOfFourOfAKindFirstHand.getCardValue() > getValueOfFourOfAKindSecondHand.getCardValue()) ? 1 : 2;
    }
}
