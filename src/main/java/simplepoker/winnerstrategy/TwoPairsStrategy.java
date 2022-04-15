package simplepoker.winnerstrategy;

import simplepoker.Card;
import simplepoker.enums.CardValue;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static simplepoker.winnerstrategy.StrategyHelperFunctions.getKeyByValue;
import static simplepoker.winnerstrategy.StrategyHelperFunctions.sortPokerHand;


public class TwoPairsStrategy implements WinnerStrategy {

    private Map<CardValue, Integer> getAllPairsOfHand(List<Card> pokerHand) {
        return StrategyHelperFunctions
                .getPokerHandValues(pokerHand)
                .entrySet()
                .stream()
                .filter(x -> x.getValue() == 2)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private CardValue getRemaningCard(List<Card> pokerHand) {
        return  getKeyByValue(StrategyHelperFunctions.getPokerHandValues(pokerHand), 1);
    }

    private Integer getWinnerWithRemaningCard(List<Card> firstHand, List<Card> secondHand) {
        CardValue getRemainingFirstHand = getRemaningCard(firstHand);
        CardValue getRemainingSecondHand = getRemaningCard(secondHand);
        return (getRemainingFirstHand.getCardValue() > getRemainingSecondHand.getCardValue()) ? 1 : 2;
    }

    @Override
    public Integer computeWinner(List<Card> firstHand, List<Card> secondHand) {
        Map<CardValue, Integer> twoPairsOfFirstHand = getAllPairsOfHand(firstHand);
        Map<CardValue, Integer> twoPairsOfSecondHand = getAllPairsOfHand(secondHand);
        List<Integer> cardValuesOfFirstHand = sortPokerHand(twoPairsOfFirstHand);
        List<Integer> cardValuesOfSecondHand = sortPokerHand(twoPairsOfSecondHand);
        Integer winner = null;

        for (Integer ignored : cardValuesOfFirstHand) {
            int compare = Integer.compare(Collections.max(cardValuesOfFirstHand), Collections.max(cardValuesOfSecondHand));
            switch (compare) {
                case -1 -> winner = 2;
                case 0 -> winner = getWinnerWithRemaningCard(firstHand, secondHand);
                case 1 -> winner = 1;
            }
            cardValuesOfFirstHand.remove(cardValuesOfFirstHand.size() - 1);
            cardValuesOfSecondHand.remove(cardValuesOfSecondHand.size() - 1);
        }
        return winner;
    }
}
