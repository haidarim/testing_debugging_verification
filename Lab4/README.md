# Lab 4: Formal Verification

## Question 1

### Error Message Explanation:

Dafny reports an error message stating that it cannot verify the postcondition `ensures a > b`. This is because Dafny cannot infer that either `x > y` or `y > x` holds true after the conditional statement, so it cannot guarantee that `a` will always be greater than `b`.

### Weakest Precondition Calculus Derivation:

The derivation fails at the step where we apply the conditional rule. Since Dafny cannot infer the conditions `x > y` or `y > x`, it cannot proceed with the proof.

### Fix Possibilities:

1. **Postcondition Fix (Method M1):** Modify the postcondition to ensure that `a` is greater than `b` under all circumstances.
2. **Precondition Addition (Method M2):** Add a precondition to ensure that either `x > y` or `y > x`.
3. **Implementation Fix (Method M3):** Change the implementation of the method to ensure that `a` is always greater than `b`.

## Question 2

### Specification and Proof in Dafny:

1. Complete the specification for the ComputeFact method, including preconditions, postconditions, and loop annotations.
2. Use the weakest precondition calculus steps to prove partial correctness of the ComputeFact method.
3. Extend the proof of partial correctness to prove total correctness.

## Question 3

### CircularMemory Extension:

Extend the CircularMemory class to include a method DoubleCapacity() that doubles the capacity of the underlying array while maintaining certain properties.
