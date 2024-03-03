import org.junit.jupiter.api.Test;

import java.net.NetworkInterface;

import static org.junit.jupiter.api.Assertions.*;

public class setRequiredNumberTests {

    // partition1 test
    // input: ("xnEmployee = 0", "startTime = 4", "endTime = 2"),
    // setRequiredNumber(nEmployee:int, startTime:int, endTime:int) = "unchanged"
    @Test
    void test1() {
        WorkSchedule ws = new WorkSchedule(5);
        ws.addWorkingPeriod("e1", 0, 4);
        ws.setRequiredNumber(1, 0, 4);

        assertEquals(1, ws.readSchedule(0).requiredNumber);
        ws.setRequiredNumber(0, 4, 2);
        assertEquals(1, ws.readSchedule(0).requiredNumber);
    }

    // input: ("xnEmployee = 0", "startTime = 2", "endTime = 4") &&
    // workingEmployee.len == 1, setRequiredNumber(nEmployee:int, startTime:int,
    // endTime:int)
    // ==> "requiredNumber" == "nEmployee" && "workingEmployee.len" == "nEmployee"
    @Test
    void test2() {
        WorkSchedule ws = new WorkSchedule(5);
        ws.addWorkingPeriod("e1", 0, 4);
        ws.setRequiredNumber(0, 2, 4);

        assertEquals(0, ws.readSchedule(0).requiredNumber);
        assertEquals(0, ws.readSchedule(0).workingEmployees.length);
    }

    // input: ("xnEmployee = 1", "startTime = 2", "endTime = 4") &&
    // workingEmployee.len == 0, setRequiredNumber(nEmployee:int, startTime:int,
    // endTime:int)
    // ==> "requiredNumber" == "nEmployee" && "workingEmployee" unchanged
    @Test
    void test3() {
        WorkSchedule ws = new WorkSchedule(5);
        ws.setRequiredNumber(1, 0, 4);

        assertEquals(1, ws.readSchedule(0).requiredNumber);
        assertEquals(0, ws.readSchedule(0).workingEmployees.length);
    }

    @Test
    void test4() {
        WorkSchedule ws = new WorkSchedule(5);
        assertDoesNotThrow(() -> {
            ws.setRequiredNumber(1, 80, Integer.MAX_VALUE);
        });
    }
}
