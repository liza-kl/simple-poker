package simplepoker.winnerstrategy;

import simplepoker.Card;
import simplepoker.enums.CardValue;
import simplepoker.PokerHandService;

import java.util.List;
import static simplepoker.winnerstrategy.StrategyHelperFunctions.getKeyByValue;

public class FourOfAKindStrategy implements WinnerStrategy{
    @Override
    public Integer computeWinner(List<Card> firstHand, List<Card> secondHand) {
        CardValue getValueOfFourOfAKindFirstHand = getKeyByValue(PokerHandService.getPokerHandValues(firstHand), 4);
        CardValue getValueOfFourOfAKindSecondHand = getKeyByValue(PokerHandService.getPokerHandValues(secondHand), 4);
        return (getValueOfFourOfAKindFirstHand.value > getValueOfFourOfAKindSecondHand.value) ? 1 : 2;
    }
}
