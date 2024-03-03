# Lab 2: Debugging

In this lab, we will be debugging the program `Dates.class`.

## Task 1: Probing around

After obtaining `Dates.class`, execute the program by calling `java Dates` with four arguments: month day month day. The program calculates the day differences between two input dates. Your task is to come up with three more test cases that show that `Dates.class` is buggy.

### Test Cases:

1. Input: `1 2 3 4`
   Output: The difference in days between 1/2 and 3/4 is: 33
   (If you see an error, go to Oracle and download the latest JDK.)

2. Input: `[Your test case]`
   Output: `[Output]`

3. Input: `[Your test case]`
   Output: `[Output]`

4. Input: `[Your test case]`
   Output: `[Output]`

## Task 2: Put on debugging lens

Using `jdb`, explore the program from inside the debugger using `step`, `list`, and `locals`. Report all local variables inside `Dates.main`.

## Task 3: Other method

Identify the name and location of the method inside `Dates.class` other than `Dates.main`. Set a breakpoint at this method.

## Task 4: Finding the bug(s?)

File a report documenting the bugs you have located and the corresponding line numbers in `BugReport.txt`.

## Task 5: Reconstruct a correct DatesImprove.java

Create a new file called `DatesImprove.java` based on `Dates.java` and fix all the bugs.

## Task 6: Create your test suite

Submit your test suite showcasing the fixes you've implemented in `DatesImprove.java`. Think about corner cases and include them in your test suite. The test suite should be a text file called `DateTestSuite.txt` and contain rows of four integers.
