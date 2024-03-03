import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


public class nextIncompleteTests {

    // x ∈ {0, ... ,size-1} for i = currentTime to < size when h = ws.readSchedule(i).workingEmployee.len < h.requiredNumber
    // then return i;
    @Test void test1(){
        WorkSchedule ws = new WorkSchedule(10);
        ws.addWorkingPeriod("e0t4", 0, 4);
        ws.setRequiredNumber(2,0,4);
        assertEquals(4, ws.nextIncomplete(0));
        ws.addWorkingPeriod("e4t8", 4, 8);
        ws.setRequiredNumber(2,4,8);
        assertEquals(8, ws.nextIncomplete(0));
    }

    //x ∈ {0, ... ,size-1} for i = currentTime to < size when h = ws.readSchedule(i).workingEmployee.len >= h.requiredNumber
    //then return -1;
    @Test void test2(){
        WorkSchedule ws = new WorkSchedule(10);
        ws.setRequiredNumber(1,0,4);
        ws.addWorkingPeriod("e0_0t4", 0, 4);
        ws.addWorkingPeriod("e1_0t4", 0, 4);
        assertEquals(-1, ws.nextIncomplete(0));
    }

    @Test void test3(){
        WorkSchedule ws = new WorkSchedule(50);
        ws.setRequiredNumber(1, 0, 49);
        ws.addWorkingPeriod("mehdi", 0, 49);
        assertEquals(-1, ws.nextIncomplete(0));
    }


    @Test void test4(){
        WorkSchedule ws = new WorkSchedule(50);
        ws.setRequiredNumber(4, 0, 49);
        ws.addWorkingPeriod("theo", 0, 49);
        assertEquals(0, ws.nextIncomplete(0));
    }


    @Test void test5(){
        WorkSchedule ws = new WorkSchedule(50);
        ws.setRequiredNumber(5, 0, 49);
        ws.addWorkingPeriod("theo", 0, 10);
        assertEquals(10, ws.nextIncomplete(0));
    }
}




