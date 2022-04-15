package simplepoker.winnerstrategy;

import simplepoker.Card;
import simplepoker.enums.CardValue;
import simplepoker.PokerHandService;

import java.util.*;

import static simplepoker.PokerHandService.getKeyByValue;

public class PairStrategy implements WinnerStrategy {
    @Override
    public Integer computeWinner(List<Card> firstHand, List<Card> secondHand) {
        CardValue getValueOfThreeKindFirstHand = getKeyByValue(PokerHandService.getPokerHandValues(firstHand), 2);
        CardValue getValueOfThreeKindSecondHand = getKeyByValue(PokerHandService.getPokerHandValues(secondHand), 2);
        if (getValueOfThreeKindFirstHand.value > getValueOfThreeKindSecondHand.value) {
            return 1;
        }
        if (getValueOfThreeKindFirstHand.value < getValueOfThreeKindSecondHand.value) {
            return 2;
        }

        Set<CardValue> firstList = PokerHandService.getPokerHandValues(firstHand).keySet();
        Set<CardValue> secondList = PokerHandService.getPokerHandValues(secondHand).keySet();
        firstList.removeIf(value -> value.value == getValueOfThreeKindFirstHand.value);
        secondList.removeIf(value -> value.value == getValueOfThreeKindSecondHand.value);

        List<Integer> firstBla = new ArrayList<>();
        List<Integer> secondBla = new ArrayList<>();
        firstList.forEach(it -> firstBla.add(it.value));
        secondList.forEach(it -> secondBla.add(it.value));
        Collections.sort(firstBla);
        Collections.sort(secondBla);

        for(int i = 0; i <= firstBla.size(); i++) {
            if(Collections.max(firstBla) > Collections.max(secondBla)) {
                return 1;
            }
            if(Collections.max(secondBla) > Collections.max(firstBla)) {
                return 2;
            }
                firstBla.remove(firstBla.size() - 1);
                secondBla.remove(secondBla.size() - 1);
                i++;

        }
        return 0;
    }
}
