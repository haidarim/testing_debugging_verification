import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.function.IntBinaryOperator;

public class distinctClosedTests {

    private Set myTestArray;
    
    @BeforeEach
    void setup(){
        myTestArray = new Set();
    }
        
    @Test
    public void distinctClosedTrue() {   
        myTestArray.insert(-1);
        myTestArray.insert(0);
        myTestArray.insert(1);
        
        IntBinaryOperator addition = (a, b) -> a + b;
        
        assertTrue(myTestArray.distinctClosed(addition));
    }
    
    @Test
    public void distinctClosedFalse() {   
        myTestArray.insert(-1);
        myTestArray.insert(0);
        
        IntBinaryOperator subtraction = (a, b) -> a - b;
        
        assertFalse(myTestArray.distinctClosed(subtraction));
    }
}
