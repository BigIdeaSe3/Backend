package commands;

import com.joris.drawsomethingbackend.commands.GetSubjects;
import com.joris.drawsomethingbackend.commands.StartGame;
import com.joris.drawsomethingbackend.controllers.GameController;
import com.joris.drawsomethingbackend.models.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CommandsTest {

    private Game game = new Game();
    StartGame command = new StartGame(new GameController());

    @BeforeEach
    public void setup(){
        game.addPlayer(new Player("Test", "Test"));
        game.addPlayer(new Player("Test2", "Test2"));
        game.setSubject(new Subject("Appel"));
        game.setThickness(new Thickness(4));
        game.setCurrentColor(new Color("#FFFFFF"));
    }

    @Test
    public void startGameAndGetDrawer() {
        Player player = game.startGame();
        Assertions.assertNotNull(player);
    }

    @Test
    public void startDrawing() {
        Location loc = new Location(1,1);
        Location location = game.startDrawing(loc);
        Assertions.assertEquals(loc,location);
    }

    @Test
    public void stopDrawing() {
        Assertions.assertTrue(game.stopDrawing());
    }

    @Test
    public void setThickness() {
        Thickness thicc = game.getThickness();
        game.setThickness(new Thickness(5));
        Assertions.assertNotEquals(game.getThickness(),thicc);
    }

    @Test
    public void setSubject() {
        Subject subject = game.getSubject();
        game.setSubject(new Subject("Test"));
        Assertions.assertNotEquals(game.getSubject(), subject);
    }

    @Test
    public void setColor() {
        Color c = game.getCurrentColor();
        game.setCurrentColor(new Color("#000000"));
        Assertions.assertNotEquals(game.getCurrentColor(), c);
    }

    @Test
    public void leaveGame() {
        int size = game.getConnectedPlayers().size();
        game.removePlayer(new Player("Test", "Test"));
        Assertions.assertEquals(size-1, game.getConnectedPlayers().size());
    }

    @Test
    public void joinGame() {
        int size = game.getConnectedPlayers().size();
        game.addPlayer(new Player("Test3", "Test3"));
        Assertions.assertEquals(size+1, game.getConnectedPlayers().size());
    }

    @Test
    public void joinGameWhenJoined() {
        int size = game.getConnectedPlayers().size();
        game.addPlayer(new Player("Test", "Test"));
        Assertions.assertEquals(size, game.getConnectedPlayers().size());
    }

    @Test
    public void guessSubjectCorrect() {
        Assertions.assertTrue(game.getSubject().guess(new Subject("Appel")));
    }

    @Test
    public void guessSubjectIncorrect() {
        Assertions.assertFalse(game.getSubject().guess(new Subject("Peer")));
    }

    @Test
    public void get3Subjects() {
        GetSubjects command = new GetSubjects(new GameController());
        List<Subject> subjects = command.execute(game,null);
        Assertions.assertEquals(3, subjects.size());
    }


    @Test
    public void getRandomSubjects() {
        GetSubjects command = new GetSubjects(new GameController());
        List<Subject> subjects = new ArrayList<>(command.execute(game,null));
        Assertions.assertNotEquals(subjects, command.execute(game,null));
    }

    @Test
    public void getAllPlayers() {
        Assertions.assertEquals(2,game.getConnectedPlayers().size());
    }

    @Test
    public void clearDrawing() {
        int size = game.getLocations().size();
        game.clearDrawing();
        Assertions.assertNotEquals(size,game.getLocations().size());
    }

    @Test
    public void drawPoint() {
        Location location = new Location(1,1);
        Assertions.assertEquals(location, game.addPoint(location));
    }

}
