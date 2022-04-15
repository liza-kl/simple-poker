package simplepoker.winnerstrategy;

import simplepoker.Card;
import simplepoker.PokerHandService;
import simplepoker.enums.CardValue;

import java.util.*;

public class StrategyHelperFunctions {

    public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
        for (Map.Entry<T, E> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }


    public static List<Integer> sortPokerHand(Map<CardValue, Integer> hand) {
        List<Integer> cardValuesOfPokerHand = new ArrayList<>();
        hand.keySet().forEach(it -> cardValuesOfPokerHand.add(it.value));
        Collections.sort(cardValuesOfPokerHand);
        return cardValuesOfPokerHand;
    }

    public static List<Integer> getIntegerCardValuesOfHand(List<Card> hand) {
        Set<CardValue> firstHandValues = PokerHandService.getPokerHandValues(hand).keySet();
        List<Integer> handIntValues = new ArrayList<>();
        firstHandValues.forEach(it -> handIntValues.add(it.value));
        return handIntValues;
    }

    public static Integer getWinnerByHighestCard(List<Integer> firstHandIntValues, List<Integer> secondHandIntValues) {
        for (int i = 0; i <= firstHandIntValues.size(); i++) {
            if (Collections.max(firstHandIntValues) > Collections.max(secondHandIntValues)) {
                return 1;
            }
            if (Collections.max(secondHandIntValues) > Collections.max(firstHandIntValues)) {
                return 2;
            } else {
                firstHandIntValues.remove(firstHandIntValues.size() - 1);
                secondHandIntValues.remove(secondHandIntValues.size() - 1);
                i++;
            }
        }
        return null;
    }
}
