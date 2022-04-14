package simplepoker.winnerstrategy;

import simplepoker.Card;
import simplepoker.CardValue;
import simplepoker.PokerHandService;

import java.util.*;

public class ThreeOfAKind implements WinnerStrategy{

    private static <T, E> T getKeyByValue(Map<T, E> map, E value) {
        for (Map.Entry<T, E> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    @Override
    public Integer computeWinner(List<Card> firstHand, List<Card> secondHand) {
        CardValue getValueOfThreeKindFirstHand = getKeyByValue(PokerHandService.getPokerHandValues(firstHand), 3);
        CardValue getValueOfThreeKindSecondHand = getKeyByValue(PokerHandService.getPokerHandValues(secondHand), 3);
        assert getValueOfThreeKindFirstHand != null;
        assert getValueOfThreeKindSecondHand != null;
        return (getValueOfThreeKindFirstHand.value > getValueOfThreeKindSecondHand.value) ? 1 : 2;
    }
}
