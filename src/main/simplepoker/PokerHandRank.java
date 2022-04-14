package simplepoker;

public enum PokerHandRank {
    HIGHCARD(0),
    PAIR(1),
    TWOPAIRS(2),
    THREEOFAKIND(3),
    FOUROFAKIND(4),
    FLUSH(5),
    FULLHOUSE(6),
    STRAIGHT(7);

    public final Integer pokerHandRankValue;

    PokerHandRank(int pokerHandRankValue) {
        this.pokerHandRankValue = pokerHandRankValue;
    }
}
