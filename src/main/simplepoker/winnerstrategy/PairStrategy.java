package simplepoker.winnerstrategy;

import simplepoker.Card;
import simplepoker.enums.CardValue;
import simplepoker.PokerHandService;

import java.util.*;

import static simplepoker.winnerstrategy.StrategyHelperFunctions.getKeyByValue;
import static simplepoker.winnerstrategy.StrategyHelperFunctions.getWinnerByHighestCard;

public class PairStrategy implements WinnerStrategy {

    @Override
    public Integer computeWinner(List<Card> firstHand, List<Card> secondHand) {
        CardValue valueOfPairFirstHand = getKeyByValue(PokerHandService.getPokerHandValues(firstHand), 2);
        CardValue valueOfPairSecondHand = getKeyByValue(PokerHandService.getPokerHandValues(secondHand), 2);
        int compare = Integer.compare(valueOfPairFirstHand.value, valueOfPairSecondHand.value);
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
        Set<CardValue> firstList = PokerHandService.getPokerHandValues(hand).keySet();
        firstList.removeIf(value -> Objects.equals(value.value, cardValuesOfHand.value));
        List<Integer> remainingCardsFirstHand = new ArrayList<>();
        firstList.forEach(it -> remainingCardsFirstHand.add(it.value));
        Collections.sort(remainingCardsFirstHand);
        return remainingCardsFirstHand;
    }

}
