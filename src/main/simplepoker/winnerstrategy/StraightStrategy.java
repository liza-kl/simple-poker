package simplepoker.winnerstrategy;

import simplepoker.Card;

import java.util.*;

import static simplepoker.winnerstrategy.StrategyHelperFunctions.getIntegerCardValuesOfHand;

public class StraightStrategy implements WinnerStrategy{
    @Override
    public Integer computeWinner(List<Card> firstHand, List<Card> secondHand) {
        List<Integer> firstHandIntValues = getIntegerCardValuesOfHand(firstHand);
        List<Integer> secondHandIntValues = getIntegerCardValuesOfHand(secondHand);
        return (Collections.max(firstHandIntValues) > Collections.max(secondHandIntValues)) ? 1 : 2;
    }
}
