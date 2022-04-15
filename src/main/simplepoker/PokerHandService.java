package simplepoker;

import simplepoker.enums.CardSuit;
import simplepoker.enums.CardValue;
import simplepoker.enums.PokerHand;
import simplepoker.rules.*;
import simplepoker.rules.Flush;
import simplepoker.rules.Pair;
import simplepoker.rules.TwoPairs;
import simplepoker.winnerstrategy.*;

import java.util.*;

public class PokerHandService {
    private static WinnerStrategy winnerStrategy = new RegularWinnerStrategy();

    public static WinnerStrategy getWinnerStrategy() {
        return winnerStrategy;
    }
    public static Map<CardSuit, Integer> getPokerHandSuitValues(List<Card> pokerHand) {
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

    public static Map<CardValue, Integer> getPokerHandValues(List<Card> pokerHand) {
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

    public static PokerHand getPokerHand(List<Card> pokerHand) {
        List<PokerHandRule> listOfRulesToCheck = List.of(
                new StraightFlush(),
                new FullHouse(),
                new Flush(),
                new FourOfAKind(),
                new ThreeOfAKind(),
                new TwoPairs(),
                new Pair());

        return listOfRulesToCheck
                .stream()
                .filter(it -> it.isSatisfiedBy(pokerHand))
                .findFirst()
                .orElse(new HighCard())
                .returnCorrespondingRank();
    }

    public static Integer computeWinner(List<Card> firstHand, List<Card> secondHand) {
        PokerHand firstPokerHand = getPokerHand(firstHand);
        PokerHand secondPokerHand = getPokerHand(secondHand);

        if (firstPokerHand != secondPokerHand) {
            winnerStrategy = new RegularWinnerStrategy();
            return winnerStrategy.computeWinner(firstHand, secondHand);
        }
        winnerStrategy = switch (firstPokerHand) {
            case STRAIGHT -> new StraightStrategy();
            case FULLHOUSE, THREEOFAKIND -> new ThreeOfAKindStrategy();
            case FLUSH, HIGHCARD ->  new HighestCardStrategy();
            case FOUROFAKIND -> new FourOfAKindStrategy();
            case TWOPAIRS ->  new TwoPairsStrategy();
            case PAIR -> new PairStrategy();
        };
        return winnerStrategy.computeWinner(firstHand, secondHand);

    }
}
