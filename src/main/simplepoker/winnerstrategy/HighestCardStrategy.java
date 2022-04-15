package simplepoker.winnerstrategy;

import simplepoker.Card;
import simplepoker.enums.CardValue;
import simplepoker.PokerHandService;

import java.util.*;

public class HighestCardStrategy implements WinnerStrategy {
    @Override
    public Integer computeWinner(List<Card> firstHand, List<Card> secondHand) {
        Set<CardValue> firstHandValues = PokerHandService.getPokerHandValues(firstHand).keySet();
        Set<CardValue> secondHandValues = PokerHandService.getPokerHandValues(secondHand).keySet();
        List<Integer> firstBla = new ArrayList<>();
        List<Integer> secondBla = new ArrayList<>();
        firstHandValues.forEach(it -> firstBla.add(it.value));
        secondHandValues.forEach(it -> secondBla.add(it.value));
        Collections.sort(firstBla);
        Collections.sort(secondBla);

        for(int i = 0; i <= firstBla.size(); i++) {
            if(Collections.max(firstBla) > Collections.max(secondBla)) {
                return 1;
            }
            if(Collections.max(secondBla) > Collections.max(firstBla)) {
                return 2;
            } else {
                firstBla.remove(firstBla.size() - 1);
                secondBla.remove(secondBla.size() - 1);
                i++;
            }
        }
        return 0;
    }
}
