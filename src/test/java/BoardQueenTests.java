import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class BoardQueenTests {
    Board SUT;

    @BeforeEach
    public void prepareBoard(){
        SUT = new Board(8, 8);
    }
    @AfterEach
    public void showTestedBoard(){
        SUT.showBoard();
    }

    @Test
    public void checkEmptyBoard() {
        SUT.evaluateBoard();

        List<Square> attackedSquares = SUT.getAttackedSquares();
        assertThat(attackedSquares, hasSize(0));
    }

    @Test
    public void checkOneQueenInTheCenter() {
        SUT.addQueen(4, 4);
        SUT.evaluateBoard();



        List<Square> attackedSquares = SUT.getAttackedSquares();
        assertThat(attackedSquares, hasSize(27));
    }

    @Test
    public void checkOneQueenOnTheEdgeA() {
        SUT.addQueen(1, 4);
        SUT.evaluateBoard();



        List<Square> attackedSquares = SUT.getAttackedSquares();
        assertThat(attackedSquares, hasSize(21));
    }

    @Test
    public void checkOneQueenOnTheEdgeH() {
        SUT.addQueen(8, 4);
        SUT.evaluateBoard();



        List<Square> attackedSquares = SUT.getAttackedSquares();
        assertThat(attackedSquares, hasSize(21));
    }

    @Test
    public void checkOneQueenOnTheEdge8() {
        SUT.addQueen(4, 8);
        SUT.evaluateBoard();



        List<Square> attackedSquares = SUT.getAttackedSquares();
        assertThat(attackedSquares, hasSize(21));
    }

    @Test
    public void checkOneQueenOnTheEdge1() {
        SUT.addQueen(4, 1);
        SUT.evaluateBoard();



        List<Square> attackedSquares = SUT.getAttackedSquares();
        assertThat(attackedSquares, hasSize(21));
    }


    @Test
    public void checkOneQueenInTheCornerA1() {
        SUT.addQueen(1, 1);
        SUT.evaluateBoard();

        List<Square> attackedSquares = SUT.getAttackedSquares();
        assertThat(attackedSquares, hasSize(21));
    }

    @Test
    public void checkOneQueenInTheCornerA8() {
        SUT.addQueen(1, 8);
        SUT.evaluateBoard();



        List<Square> attackedSquares = SUT.getAttackedSquares();
        assertThat(attackedSquares, hasSize(21));
    }

    @Test
    public void checkOneQueenInTheCornerH1() {
        SUT.addQueen(8, 1);
        SUT.evaluateBoard();



        List<Square> attackedSquares = SUT.getAttackedSquares();
        assertThat(attackedSquares, hasSize(21));
    }

    @Test
    public void checkOneQueenInTheCornerH8() {
        SUT.addQueen(8, 8);
        SUT.evaluateBoard();

        List<Square> attackedSquares = SUT.getAttackedSquares();
        assertThat(attackedSquares, hasSize(21));
    }

    @Test
    public void checkTwoQueensOnOneSquare() {
        SUT.addQueen(5, 3);
        assertThrows(IllegalArgumentException.class, () -> SUT.addQueen(5, 3));
    }

    @Test
    public void checkTwoQueensAttackingEachOther() {
        SUT.addQueen(3, 6);
        SUT.addQueen(6, 3);
        SUT.evaluateBoard();

        List<Square> attackedSquares = SUT.getAttackedSquares();
        assertThat(attackedSquares, hasSize(42));
        assertThat(SUT.getSquare(3, 6).getCount(), is(1));
        assertThat(SUT.getSquare(6, 3).getCount(), is(1));
        assertThat(SUT.getSquare(3, 3).getCount(), is(2));
        assertThat(SUT.getSquare(4, 5).getCount(), is(2));
    }

    @Test
    public void checkForMostAttackedSquarePossible(){
        SUT.addQueen(1, 1);
        SUT.addQueen(1, 2);
        SUT.addQueen(1, 3);
        SUT.addQueen(2, 1);
        SUT.addQueen(2, 3);
        SUT.addQueen(3, 1);
        SUT.addQueen(3, 2);
        SUT.addQueen(3, 3);

        SUT.evaluateBoard();

        assertThat(SUT.getSquare(2, 2).getCount(), is(8));
    }

    @Test
    public void checkAlmostFullBoard(){
        for(int i = 1; i <= SUT.getFiles(); i++){
            for (int j = 1; j <= SUT.getRanks(); j++){
                if (i == 1 && j == 1) continue;
                SUT.addQueen(i, j);
            }
        }

        SUT.evaluateBoard();

        assertThat(SUT.getSquare(1, 1).getCount(), is(3));
    }
}
