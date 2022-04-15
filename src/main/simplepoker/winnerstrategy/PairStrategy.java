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
        if (valueOfPairFirstHand.value > valueOfPairSecondHand.value) {
            return 1;
        }
        if (valueOfPairFirstHand.value < valueOfPairSecondHand.value) {
            return 2;
        }
        return computeWinnerWithRemainingCards(firstHand, secondHand, valueOfPairFirstHand, valueOfPairSecondHand);
    }

    private Integer computeWinnerWithRemainingCards(List<Card> firstHand,
                                                    List<Card> secondHand,
                                                    CardValue valueOfPairFirstHand,
                                                    CardValue valueOfPairSecondHand) {
        Set<CardValue> firstList = PokerHandService.getPokerHandValues(firstHand).keySet();
        Set<CardValue> secondList = PokerHandService.getPokerHandValues(secondHand).keySet();
        firstList.removeIf(value -> Objects.equals(value.value, valueOfPairFirstHand.value));
        secondList.removeIf(value -> Objects.equals(value.value, valueOfPairSecondHand.value));

        List<Integer> remainingCardsFirstHand = new ArrayList<>();
        List<Integer> remainingCardsSecondHand = new ArrayList<>();
        firstList.forEach(it -> remainingCardsFirstHand.add(it.value));
        secondList.forEach(it -> remainingCardsSecondHand.add(it.value));

        Collections.sort(remainingCardsFirstHand);
        Collections.sort(remainingCardsSecondHand);

        Integer x = getWinnerByHighestCard(remainingCardsFirstHand, remainingCardsSecondHand);
        if (x != null) return x;
        return 0;
    }
}
