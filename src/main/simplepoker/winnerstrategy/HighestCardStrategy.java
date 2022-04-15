package simplepoker.winnerstrategy;

import simplepoker.Card;

import java.util.*;

import static simplepoker.winnerstrategy.StrategyHelperFunctions.getIntegerCardValuesOfHand;
import static simplepoker.winnerstrategy.StrategyHelperFunctions.getWinnerByHighestCard;

public class HighestCardStrategy implements WinnerStrategy {
    @Override
    public Integer computeWinner(List<Card> firstHand, List<Card> secondHand) {
        List<Integer> firstHandIntValues = sortPokerHand(firstHand);
        List<Integer> secondHandIntValues = sortPokerHand(secondHand);
        Integer x = getWinnerByHighestCard(firstHandIntValues, secondHandIntValues);
        if (x != null) return x;
        return 0;
    }

    private List<Integer> sortPokerHand(List<Card> hand) {
        List<Integer> handIntValues = getIntegerCardValuesOfHand(hand);
        Collections.sort(handIntValues);
        return handIntValues;
    }

}
