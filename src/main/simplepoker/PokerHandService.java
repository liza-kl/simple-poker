package simplepoker;

import simplepoker.rules.*;
import simplepoker.winnerstrategy.HighestCard;
import simplepoker.winnerstrategy.RegularWinner;
import simplepoker.winnerstrategy.WinnerStrategy;

import java.util.*;

public class PokerHandService {
    List<Card> pokerHand;
    private static WinnerStrategy winnerStrategy = new RegularWinner();

    PokerHandService(List<Card> pokerHand) {
        this.pokerHand = pokerHand;
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


    public Integer computeWinner(List<Card> firstHand, List<Card> secondHand)
    {
        Integer winner = -1;
        PokerHandRank firstRank = getPokerHandRank(firstHand);
        PokerHandRank secondRank = getPokerHandRank(secondHand);
        if (firstRank != secondRank) {
            winner = winnerStrategy.computeWinner(firstHand, secondHand);
        }
        if (firstRank == PokerHandRank.HIGHCARD) {
            winnerStrategy = new HighestCard();
            winner = winnerStrategy.computeWinner(firstHand,secondHand);
        }
        if(firstRank == PokerHandRank.PAIR) {

        }
        return winner;

    }
}
