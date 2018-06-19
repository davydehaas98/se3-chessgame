package unit;

import model.Game;
import model.interfaces.IGame;
import org.junit.Before;
import org.junit.Test;
import stubs.ServerMessageGeneratorStub;

import java.awt.*;

import static org.junit.Assert.*;

public class GameUnitTest {
    private IGame game;

    @Before
    public void setup() {
        game = new Game(new ServerMessageGeneratorStub());
        game.setBoard();
    }

    @Test
    public void RookRules() {
        assertEquals(0, game.getBoard()[0][0].getPiece().getLegalMoves(game.getBoard()).size());
    }

    @Test
    public void PawnRules() {
        assertTrue(game.getBoard()[1][0].getPiece().getLegalMoves(game.getBoard()).size() > 0);
    }

    @Test
    public void KnightRules() {
        assertTrue(game.getBoard()[0][1].getPiece().getLegalMoves(game.getBoard()).size() > 0);
    }

    @Test
    public void CurrentTurnTest() {
        game.startGame();
    }

    @Test
    public void validMoveTest() {
        assertTrue(game.makeMove(new Point(1, 1), new Point(1, 3), null));
    }

    @Test
    public void invalidMoveTest() {
        assertFalse(game.makeMove(new Point(8, 8), new Point(8, 9), null));
    }
}
