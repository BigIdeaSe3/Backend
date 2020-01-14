package componentTests;

import com.joris.drawsomethingbackend.models.Randomizer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RandomizerTest {
    Randomizer component;

    List<String> words = Arrays.asList("Test1", "Test2", "Test3");

    @BeforeEach
    public void setup() {
        component = new Randomizer(words);
    }


    @Test
    public void testRandomize() {
        List<String> result = component.randomize(new ArrayList<>(words));

        Assertions.assertNotEquals(result,words);
    }
}
