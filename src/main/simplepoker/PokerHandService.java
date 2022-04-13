package simplepoker;

import java.util.*;

public class PokerHandService {
    List<Card> pokerHand;

    PokerHandService(List<Card> pokerHand) {
        this.pokerHand = pokerHand;
    }

    protected HashMap<CardSuit, Integer> getPokerHandSuitValues() {
        HashMap<CardSuit, Integer> suitValues = new HashMap<>();
        this.pokerHand.forEach(card -> {
            CardSuit cardSuit = card.getCardSuit();
            if (!suitValues.containsKey(cardSuit)) {
                suitValues.put(cardSuit, 1);
            } else {
                suitValues.put(cardSuit, suitValues.get(cardSuit) + 1);
            }
        });
        return suitValues;
    }

    protected HashMap<CardValue, Integer> getPokerHandValues() {
        HashMap<CardValue, Integer> cardValues = new HashMap<>();
        this.pokerHand.forEach(card -> {
            CardValue cardValue = card.getCardValue();
            if (!cardValues.containsKey(cardValue)) {
                cardValues.put(cardValue, 1);
            } else {
                cardValues.put(cardValue, cardValues.get(cardValue) + 1);
            }
        });
        return cardValues;
    }

    protected boolean checkIfPair() {
        return Collections.frequency(getPokerHandValues().values(), 2) == 1;
    }

    protected boolean checkIfTwoPairs() {
        return Collections.frequency(getPokerHandValues().values(), 2) == 2;
    }

    protected boolean checkIfThreeOfAKind() {
        return Collections.frequency(getPokerHandValues().values(), 3) == 1;
    }

    protected boolean checkIfFourOfAKind() {
        return Collections.frequency(getPokerHandValues().values(), 4) == 1;
    }

    protected boolean checkIfFullHouse() {
        return checkIfThreeOfAKind() && checkIfPair();
    }

    protected boolean checkIfFlush() {
        HashMap<CardSuit, Integer> pokerHandSuitValues = getPokerHandSuitValues();
        return pokerHandSuitValues.size() == 1;
    }

    protected boolean checkIfStraightFlush() {
        return checkIfFlush() && checkIfConsecutiveValues();
    }

    protected boolean checkIfConsecutiveValues() {
        Set<CardValue> pokerHandCardValues = getPokerHandValues().keySet();
        ArrayList<Integer> pokerHandIntegerValues = new ArrayList<>();
        pokerHandCardValues.forEach(it -> pokerHandIntegerValues.add(it.value));
        Collections.sort(pokerHandIntegerValues);
        for (int i = 0; i < pokerHandIntegerValues.size(); i++) {
            if (i + 1 == pokerHandIntegerValues.size()) {
                break;
            }
            if (pokerHandIntegerValues.get(i + 1) - pokerHandIntegerValues.get(i) != 1) {
                return false;
            }
        }
        return true;
    }



}
