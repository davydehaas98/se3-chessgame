package tests;

import model.Game;
import model.interfaces.IGame;
import org.junit.*;
import stubs.ServerMessageGeneratorStub;

import static org.junit.Assert.*;

public class GameTest {
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
    public void CurrentTurnTest(){
        game.startGame();
    }
    @Test
    public void validMoveTest() {
        assertTrue(game.makeMove("a2", "a4", null));
        assertNotNull(game.getBoard()[3][1].getPiece());
    }

    @Test
    public void invalidMoveTest() {
        assertFalse(game.makeMove("a7", "a9", null));
    }
}
