package simplepoker;

import simplepoker.enums.CardSuit;
import simplepoker.enums.CardValue;
import simplepoker.enums.PokerHandRank;
import simplepoker.rules.*;
import simplepoker.rules.Flush;
import simplepoker.rules.Pair;
import simplepoker.rules.TwoPairs;
import simplepoker.winnerstrategy.*;

import java.util.*;

public class PokerHandService {
    List<Card> pokerHand;
    private static WinnerStrategy winnerStrategy = new RegularWinner();

    public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
        for (Map.Entry<T, E> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    public static HashMap<CardSuit, Integer> getPokerHandSuitValues(List<Card> pokerHand) {
        HashMap<CardSuit, Integer> suitValues = new HashMap<>();
        pokerHand.forEach(card -> {
            CardSuit cardSuit = card.getCardSuit();
            if (!suitValues.containsKey(cardSuit)) {
                suitValues.put(cardSuit, 1);
            } else {
                suitValues.put(cardSuit, suitValues.get(cardSuit) + 1);
            }
        });
        return suitValues;
    }

    public static HashMap<CardValue, Integer> getPokerHandValues(List<Card> pokerHand) {
        HashMap<CardValue, Integer> cardValues = new HashMap<>();
        pokerHand.forEach(card -> {
            CardValue cardValue = card.getCardValue();
            if (!cardValues.containsKey(cardValue)) {
                cardValues.put(cardValue, 1);
            } else {
                cardValues.put(cardValue, cardValues.get(cardValue) + 1);
            }
        });
        return cardValues;
    }

    public static PokerHandRank getPokerHandRank(List<Card> pokerHand) {
        List<PokerHandRule> listOfRulesToCheck = List.of(
                new StraightFlush(),
                new FourOfAKind(),
                new FullHouse(),
                new Flush(),
                new ThreeOfAKind(),
                new Pair(),
                new TwoPairs());

        return listOfRulesToCheck
                .stream()
                .filter(it -> it.isSatisfiedBy(pokerHand))
                .findFirst()
                .orElse(new HighCard())
                .returnCorrespondingRank();
    }

    public static Integer computeWinner(List<Card> firstHand, List<Card> secondHand)
    {
        PokerHandRank firstRank = getPokerHandRank(firstHand);
        PokerHandRank secondRank = getPokerHandRank(secondHand);

        if (firstRank != secondRank) {
           winnerStrategy = new RegularWinner();
        }
        if(firstRank == PokerHandRank.STRAIGHT) {
            winnerStrategy = new StraightStrategy();
        }
        if(firstRank == PokerHandRank.FULLHOUSE) {
            winnerStrategy = new ThreeOfAKindStrategy();
        }
        if(firstRank == PokerHandRank.FLUSH) {
            winnerStrategy = new HighestCardStrategy();
        }
        if(firstRank == PokerHandRank.FOUROFAKIND) {
            winnerStrategy = new FourOfAKindStrategy();
        }
        if(firstRank == PokerHandRank.THREEOFAKIND) {
            winnerStrategy = new ThreeOfAKindStrategy();
        }
        if(firstRank == PokerHandRank.TWOPAIRS) {
            winnerStrategy = new TwoPairsStrategy();
        }
        if(firstRank == PokerHandRank.PAIR) {
            winnerStrategy = new PairStrategy();
        }
        if (firstRank == PokerHandRank.HIGHCARD) {
            winnerStrategy = new HighestCardStrategy();
        }
        return winnerStrategy.computeWinner(firstHand,secondHand);

    }
}
