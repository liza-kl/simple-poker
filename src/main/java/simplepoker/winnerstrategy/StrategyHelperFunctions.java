package simplepoker.winnerstrategy;

import simplepoker.Card;
import simplepoker.enums.CardSuit;
import simplepoker.enums.CardValue;

import java.util.*;

public class StrategyHelperFunctions {

    public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
        for (Map.Entry<T, E> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        throw new IllegalArgumentException("Unknown key");
    }

    public static Map<CardSuit, Integer> getPokerHandSuitValues(List<Card> pokerHand) {
        HashMap<CardSuit, Integer> suitValues = new HashMap<>();
        pokerHand.forEach(card -> putValue(suitValues, card.getCardSuit()));
        return suitValues;
    }

    private static <T> void putValue(HashMap<T, Integer> values, T value) {
        if (!values.containsKey(value)) {
            values.put(value, 1);
        } else {
            values.put(value, values.get(value) + 1);
        }
    }

    public static Map<CardValue, Integer> getPokerHandValues(List<Card> pokerHand) {
        HashMap<CardValue, Integer> cardValues = new HashMap<>();
        pokerHand.forEach(card -> putValue(cardValues, card.getCardValue()));
        return cardValues;
    }

    public static List<Integer> sortPokerHand(Map<CardValue, Integer> hand) {
        List<Integer> cardValuesOfPokerHand = new ArrayList<>();
        hand.keySet().forEach(it -> cardValuesOfPokerHand.add(it.getCardValue()));
        Collections.sort(cardValuesOfPokerHand);
        return cardValuesOfPokerHand;
    }

    public static List<Integer> getIntegerCardValuesOfHand(List<Card> hand) {
        Set<CardValue> firstHandValues = getPokerHandValues(hand).keySet();
        List<Integer> handIntValues = new ArrayList<>();
        firstHandValues.forEach(it -> handIntValues.add(it.getCardValue()));
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
        return 0;
    }
}
