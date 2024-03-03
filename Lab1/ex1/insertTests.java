import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class insertTests {

    private Set myTestArray;

    @BeforeEach
    void setup() {
        myTestArray = new Set();
        myTestArray.insert(1);
        myTestArray.insert(3);
        myTestArray.insert(5);
    }

    @BeforeEach
    void setUpTest() {
        int[] expected = { 1, 3, 5 };
        assertArrayEquals(expected, myTestArray.toArray());
    }


    @Test
    public void insertDupplicate() {
        myTestArray.insert(3);
        int[] expected = { 1, 3, 5 };
        assertArrayEquals(expected, myTestArray.toArray());
    }

    @Test
    public void insertMiddle() {
        myTestArray.insert(4);
        int[] expected = { 1, 3, 4, 5 };
        assertArrayEquals(expected, myTestArray.toArray());
    }

    @Test
    public void insertEnd() {
        myTestArray.insert(8);
        int[] expected = { 1, 3, 5, 8 };
        assertArrayEquals(expected, myTestArray.toArray());
    }
}
