package simplepoker.winnerstrategy;

import simplepoker.Card;
import simplepoker.enums.CardValue;
import simplepoker.PokerHandService;

import java.util.*;

public class StraightStrategy implements WinnerStrategy{
    @Override
    public Integer computeWinner(List<Card> firstHand, List<Card> secondHand) {
        Set<CardValue> firstHandValues = PokerHandService.getPokerHandValues(firstHand).keySet();
        Set<CardValue> secondHandValues = PokerHandService.getPokerHandValues(secondHand).keySet();
        List<Integer> firstHandIntValues = new ArrayList<>();
        List<Integer> secondHandIntValues = new ArrayList<>();
        firstHandValues.forEach(it -> firstHandIntValues.add(it.value));
        secondHandValues.forEach(it -> secondHandIntValues.add(it.value));
        return (Collections.max(firstHandIntValues) > Collections.max(secondHandIntValues)) ? 1 : 2;

    }
}
