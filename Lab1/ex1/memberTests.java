import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class memberTests {

    private Set myTestArray;

    @BeforeEach
    void setup(){
        myTestArray = new Set();
        myTestArray.insert(1);
        myTestArray.insert(3);
        myTestArray.insert(5);
        int[] expected = {1,3,5};
        assertArrayEquals(expected, myTestArray.toArray());
    }
    
   
    
    @Test  
    public void isMember() {
        assertTrue(myTestArray.member(3));
    }

    @Test 
    public void notMemberEnd() {
       assertFalse(myTestArray.member(6));
    }
    
    @Test  
    public void notMemberMiddle() {
        assertFalse(myTestArray.member(2));
    }  
}
