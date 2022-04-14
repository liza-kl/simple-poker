package simplepoker.winnerstrategy;

import simplepoker.Card;
import simplepoker.CardValue;
import simplepoker.PokerHandService;

import java.util.*;
import java.util.stream.Collectors;

import static simplepoker.PokerHandService.getKeyByValue;


public class TwoPairsStrategy implements WinnerStrategy {

    private Map<CardValue, Integer> getTwoPairsOfHand(List<Card> pokerHand) {
        return PokerHandService
                .getPokerHandValues(pokerHand)
                .entrySet()
                .stream()
                .filter(x -> x.getValue() == 2)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private List<Integer> getSortedCardValuesOfHand(Map<CardValue, Integer> twoPairsOfHand) {
        List<Integer> cardValuesOfPokerHand = new ArrayList<>();
        twoPairsOfHand.keySet().forEach(it -> cardValuesOfPokerHand.add(it.value));
        Collections.sort(cardValuesOfPokerHand);
        return cardValuesOfPokerHand;
    }

    private CardValue getRemaningCard(List<Card> pokerHand) {
        return  getKeyByValue(PokerHandService.getPokerHandValues(pokerHand), 1);
    }

    private Integer getWinnerWithRemaningCard(List<Card> firstHand, List<Card> secondHand) {
        CardValue getRemainingFirstHand = getRemaningCard(firstHand);
        CardValue getRemainingSecondHand = getRemaningCard(secondHand);
        return (getRemainingFirstHand.value > getRemainingSecondHand.value) ? 1 : 2;
    }

    @Override
    public Integer computeWinner(List<Card> firstHand, List<Card> secondHand) {
        Map<CardValue, Integer> twoPairsOfFirstHand = getTwoPairsOfHand(firstHand);
        Map<CardValue, Integer> twoPairsOfSecondHand = getTwoPairsOfHand(secondHand);

        List<Integer> cardValuesOfFirstHand = getSortedCardValuesOfHand(twoPairsOfFirstHand);
        List<Integer> cardValuesOfSecondHand = getSortedCardValuesOfHand(twoPairsOfSecondHand);
        Integer winner = null;

        for(int i = 0; i < cardValuesOfFirstHand.size(); i++) {
            int compare = Integer.compare(Collections.max(cardValuesOfFirstHand), Collections.max(cardValuesOfSecondHand));
            switch (compare) {
                case -1 -> winner = 1;
                case 0 -> winner = getWinnerWithRemaningCard(firstHand, secondHand);
                case 1 -> winner = 2;
            }
            cardValuesOfFirstHand.remove(cardValuesOfFirstHand.size() - 1);
            cardValuesOfSecondHand.remove(cardValuesOfSecondHand.size() - 1);
            i++;
        }
        return winner;
    }
}
