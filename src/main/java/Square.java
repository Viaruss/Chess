import pieces.Piece;

public class Square {
    public int file;
    public int rank;
    private int count;
    public Piece piece;

    public int getCount() {
        return count;
    }

    public void increaseCount() {
        count++;
    }

    public Square(int file, int rank, int count) {
        this.file = file;
        this.rank = rank;
        this.count = count;
        piece = null;
    }
}
