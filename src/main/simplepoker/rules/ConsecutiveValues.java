package simplepoker.rules;

import simplepoker.Card;
import simplepoker.CardValue;
import simplepoker.PokerHandRank;
import simplepoker.PokerHandService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class ConsecutiveValues implements PokerHandRule{
    @Override
    public boolean isSatisfiedBy(List<Card> pokerHand) {
        Set<CardValue> pokerHandCardValues = PokerHandService.getPokerHandValues(pokerHand).keySet();
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

    @Override
    public PokerHandRank returnCorrespondingRank() {
        return null;
    }
}
