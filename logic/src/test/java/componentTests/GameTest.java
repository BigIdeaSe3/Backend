package componentTests;

import com.joris.drawsomethingbackend.models.Game;
import com.joris.drawsomethingbackend.models.Location;
import com.joris.drawsomethingbackend.models.Player;
import com.joris.drawsomethingbackend.models.Subject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class GameTest {
    Game game = new Game();

    @BeforeEach
    public void setup() {
        game.addPlayer(new Player("Test","Test"));
        game.setSubject(new Subject("Appel"));
    }

    @Test
    public void removePlayer() {

        int size = game.getConnectedPlayers().size();

        game.removePlayer(new Player("Test", "Test"));

        Assertions.assertNotEquals(size,game.getConnectedPlayers().size());
    }

    @Test
    public void cantRemovePlayer() {

        int size = game.getConnectedPlayers().size();

        game.removePlayer(new Player("Test2", "Test2"));


        Assertions.assertEquals(size,game.getConnectedPlayers().size());
    }

    @Test
    public void addPlayer() {

        int size = game.getConnectedPlayers().size();

        game.addPlayer(new Player("Test2", "Test2"));


        Assertions.assertNotEquals(size,game.getConnectedPlayers().size());
    }

    @Test
    public void cantAddPlayer() {

        int size = game.getConnectedPlayers().size();

        game.addPlayer(new Player("Test", "Test"));


        Assertions.assertEquals(size,game.getConnectedPlayers().size());
    }

    @Test
    public void startDrawing() {

        int size = game.getLocations().get(game.getLocations().size()-1).y.size();

        game.startDrawing(new Location(1,1));

        Assertions.assertNotEquals(size, game.getLocations().get(game.getLocations().size()-1).y.size());
    }

    @Test
    public void stopDrawing() {

        int size = game.getLocations().size();

        game.stopDrawing();

        Assertions.assertNotEquals(size, game.getLocations().size());
    }

//    @Test
//    public void guessSubjectCorrect() {
//        Assertions.assertTrue(game.guessSubject("Appel"));
//    }
//
//
//    @Test
//    public void guessSubjectIncorrect() {
//        Assertions.assertFalse(game.guessSubject("Peer"));
//    }

    @Test
    public void addPoint() {

        int size = game.getLocations().get(game.getLocations().size()-1).y.size();

        game.addPoint(new Location(2,1));

        Assertions.assertNotEquals(size, game.getLocations().get(game.getLocations().size()-1).y.size());
    }

    @Test
    public void clearDrawing() {

        int size = game.getLocations().get(game.getLocations().size()-1).y.size();

        game.clearDrawing();

        Assertions.assertEquals(0, game.getLocations().size());
    }
}
