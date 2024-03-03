# Lab Description

This lab is divided into two parts: Manual White-box Testing and Manual Black-box Testing.

## 1. White-box Testing

For the assignments in part 1, you will be using the source code of the methods. Each directory includes a README file containing a detailed task description and an explanation of each file.

The assignment is about the class Set, which represents sets of integers. The elements of a set are stored in an ArrayList, sorted, and without duplicates to speed up some operations. The class includes methods like `intersect(Set s)` and `distinctClosed(IntBinaryOperator f)` that require detailed testing.

### Tasks:

- Use statement and branch coverage to derive test cases for `insert`.
- Use statement and branch coverage to derive test cases for `member`.
- Use statement and branch coverage to derive test cases for `intersect`.
- Use statement and branch coverage to derive test cases for `distinctClosed`.
- If you find any bugs, try to correct them in the implementation. Make a new version of `Set.java` that passes all your tests.

## 2. Black-box Testing (WorkSchedule.jar)

The assignment is about a class called WorkSchedule, which manages the schedule for the employees of a company. The constructor and methods of the class are listed below:

- `WorkSchedule(int size)`
- `Hour readSchedule(int time)`
- `setRequiredNumber(int nemployee, int starttime, int endtime)`
- `addWorkingPeriod(String employee, int starttime, int endtime)`
- `workingEmployees(int starttime, int endtime)`
- `nextIncomplete(int currenttime)`

The constructor and certain methods are trusted to be correct and do not need testing. Your task is to specify the input space, divide it into partitions, and implement test cases for each specification provided.
