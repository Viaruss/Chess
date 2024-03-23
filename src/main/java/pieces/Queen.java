package pieces;

public class Queen extends Piece {
    public Queen(){
        super();
        super.pieceChar = 'Q';
        super.directions = new int[][]{
                {0, -1}, {0, 1}, {-1, 0}, {1, 0},  // Horizontal and Vertical
                {-1, -1}, {-1, 1}, {1, -1}, {1, 1}  // Diagonal
        };
    }

    @Override
    public String toString() {
        return "" + pieceChar;
    }
}

