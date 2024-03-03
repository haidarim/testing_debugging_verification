import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class intersectTests {

    private Set myTestArray;
    private Set intersector;

    @BeforeEach
    void setup(){
        myTestArray = new Set();
        myTestArray.insert(1);
        myTestArray.insert(3);
        myTestArray.insert(5);

        int[] expected = {1,3,5};
        intersector = new Set();
        assertArrayEquals(expected, myTestArray.toArray());
        assertNotNull(this.intersector);
    }

    @Test
    public void allIntersect() {   
        intersector.insert(0);
        intersector.insert(3);
        intersector.insert(5);
        
        myTestArray.intersect(intersector);

        int[] expected = {3, 5};
        assertArrayEquals(expected, myTestArray.toArray());
    }

    @Test
    public void nonEmptyIntersectEmpty(){
        myTestArray.intersect(intersector);
        assertArrayEquals(intersector.toArray(), myTestArray.toArray());
    }

    @Test
    public void mm(){
        //A = {-1, 0, 1, 2} and B = {-1, 0}.
        Set A  = new Set();
        A.insert(-1);
        A.insert(0);
        A.insert(1);
        A.insert(2);

        Set B = new Set();
        B.insert(-1);
        B.insert(0);

        A.intersect(B);
        int[] expected = {-1, 0};
        assertArrayEquals(expected, A.toArray());
    }
}