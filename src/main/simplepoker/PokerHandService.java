package simplepoker;

import simplepoker.rules.*;

import java.util.*;

public class PokerHandService {
    List<Card> pokerHand;

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

    public PokerHandRank getPokerHandRank(List<Card> pokerHand) {
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

    public Integer computeWinnerWithSameRank(PokerHandRank rank, List<Card> firstHand, List<Card> secondHand) {
      // To be done
        return 1;
    }

    public Integer computeWinner(List<Card> firstHand, List<Card> secondHand)
    {
        PokerHandRank firstRank = getPokerHandRank(firstHand);
        PokerHandRank secondRank = getPokerHandRank(secondHand);
        if(firstRank.pokerHandRankValue > secondRank.pokerHandRankValue) {
            return 1;
        }
        if(secondRank.pokerHandRankValue > firstRank.pokerHandRankValue) {
            return 2;
        }
        else {
            return computeWinnerWithSameRank(firstRank,firstHand,secondHand);
        }
    }
}
