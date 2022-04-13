package simplepoker;

public class Card {
    private final CardSuit cardSuit;
    private final CardValue cardValue;

    public Card(CardSuit cardSuit, CardValue cardValue) {
        this.cardSuit = cardSuit;
        this.cardValue = cardValue;
    }

    public CardSuit getCardSuit() {
        return this.cardSuit;
    }

    public CardValue getCardValue() {
        return this.cardValue;
    }
}
