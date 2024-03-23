import pieces.Queen;

import java.util.Arrays;
import java.util.List;

public class Board {

    private final Square[] squares;
    private final int files;
    private final int ranks;

    public Board(int files, int ranks) {

        this.files = files;
        this.ranks = ranks;
        this.squares = new Square[files * ranks];

        for (int rank=1; rank<=ranks; rank++)
            for (int file=1; file<=files; file++)
                this.squares[index(file, rank)] = new Square(file, rank, 0);
    }

    public int getFiles() {
        return files;
    }

    public int getRanks() {
        return ranks;
    }

    private int index(int file, int rank) throws IllegalArgumentException {

        if ((file < 1 && file > files) || (rank < 1 && rank > ranks))
            throw new IllegalArgumentException("This index is out of the board");
        file--;
        rank--;

        return rank * files + file;
    }

    public Square getSquare(int file, int rank) throws IllegalArgumentException {
        return squares[index(file, rank)];
    }

    private boolean isFileCorrect(int file) {
        return file >= 1 && file <= files;
    }

    private boolean isRankCorrect(int rank) {
        return rank >= 1 && rank <= ranks;
    }

    public void addQueen(int file, int rank) throws IllegalArgumentException {
        Square s = this.squares[index(file, rank)];
        if (s.piece != null)
            throw new IllegalArgumentException("A piece has already been placed there...");
        s.piece = new Queen();
    }

    public void evaluateBoard(){
        for(Square square : squares) {
            if (square.piece != null) {
                for (int[] direction : square.piece.directions) {
                    int dirX = direction[0];
                    int dirY = direction[1];

                    int x = square.file;
                    int y = square.rank;

                    while (isFileCorrect(x + dirX) && isRankCorrect(y + dirY)) {
                        x += dirX;
                        y += dirY;
                        Square attackedSquare = getSquare(x, y);
                        attackedSquare.increaseCount();
                        if (attackedSquare.piece != null) // Break the loop if there is a piece obstructing the path
                            break;
                    }
                }
            }
        }
    }
    public List<Square> getAttackedSquares() {
        return Arrays.stream(this.squares).filter(square -> square.getCount() > 0).toList();
    }

    public void showBoard(){
        for(int i = 0; i < files; i++){
            for (int j = 0; j < ranks; j++){
                System.out.print(squares[8*i+j].piece == null ? squares[8*i+j].getCount() + " " : squares[8*i+j].piece + " ");
            }
            System.out.println();
        }
    }
}
