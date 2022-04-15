package simplepoker.winnerstrategy;

import simplepoker.Card;
import simplepoker.enums.CardValue;

import java.util.*;

import static simplepoker.winnerstrategy.StrategyHelperFunctions.getKeyByValue;
import static simplepoker.winnerstrategy.StrategyHelperFunctions.getWinnerByHighestCard;

public class PairStrategy implements WinnerStrategy {

    @Override
    public Integer computeWinner(List<Card> firstHand, List<Card> secondHand) {
        CardValue valueOfPairFirstHand = getKeyByValue(StrategyHelperFunctions.getPokerHandValues(firstHand), 2);
        CardValue valueOfPairSecondHand = getKeyByValue(StrategyHelperFunctions.getPokerHandValues(secondHand), 2);
        assert valueOfPairFirstHand != null;
        assert valueOfPairSecondHand != null;
        int compare = Integer.compare(valueOfPairFirstHand.getCardValue(), valueOfPairSecondHand.getCardValue());
        return switch (compare) {
            case -1 -> 1;
            case 0 -> computeWinnerWithRemainingCards(firstHand, secondHand, valueOfPairFirstHand, valueOfPairSecondHand);
            case 1 -> 2;
            default -> throw new IllegalStateException("Unexpected value: " + compare);
        };
    }

    private Integer computeWinnerWithRemainingCards(List<Card> firstHand,
                                                    List<Card> secondHand,
                                                    CardValue valueOfPairFirstHand,
                                                    CardValue valueOfPairSecondHand) {
        List<Integer> remainingCardsFirstHand = getSortedListOfRemainingCards(firstHand, valueOfPairFirstHand);
        List<Integer> remainingCardsSecondHand = getSortedListOfRemainingCards(secondHand, valueOfPairSecondHand);

        Integer x = getWinnerByHighestCard(remainingCardsFirstHand, remainingCardsSecondHand);
        if (x != null) return x;
        return 0;
    }

    private List<Integer> getSortedListOfRemainingCards(List<Card> hand, CardValue cardValuesOfHand) {
        Set<CardValue> firstList = StrategyHelperFunctions.getPokerHandValues(hand).keySet();
        firstList.removeIf(value -> Objects.equals(value.getCardValue(), cardValuesOfHand.getCardValue()));
        List<Integer> remainingCardsFirstHand = new ArrayList<>();
        firstList.forEach(it -> remainingCardsFirstHand.add(it.getCardValue()));
        Collections.sort(remainingCardsFirstHand);
        return remainingCardsFirstHand;
    }

}
