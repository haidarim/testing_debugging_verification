# Lab on Dafny and Program Verification

## 1. What is a Circular Buffer?

A circular buffer, or a ring buffer, refers to a data structure modeling fixed-sized memory. Interestingly, we can create a circular buffer using a linear/non-circular data structure (in our case, array in Dafny). We simulate the circular behavior using 2 position markers (`read_position` and `write_position`), one for tracking the position of reading and the other for tracking the position of writing. For a more detailed description of circular buffers, take a look at the [Wikipedia page](https://en.wikipedia.org/wiki/Circular_buffer). Don't miss the animation that shows how a circular buffer functions!

In this lab, we will implement and verify our implementation of a circular buffer.

### Buffer status:

A circular buffer is able to store a fixed amount of data. The maximum capacity is decided at the creation of the circular buffer. Inside the circular buffer, reading and writing are controlled by two pointers, indicating which cell to read from/write to next. For simplicity, our circular buffer can only store int data. A circular buffer is EMPTY when there is no data in the buffer. Initially, the buffer is created in this EMPTY status. As the buffer gradually gets filled with data, we will encounter a FULL buffer when we cannot write further (without overwriting existing unread data).

The identification of when a buffer is full or empty depends on the implementation. For example, in one possible implementation, we say that the buffer is EMPTY when the read pointer coincides with the write pointer and the buffer has not been "flipped", and the buffer is FULL when the read pointer coincides with the write pointer and the buffer has been flipped. The status of whether a buffer is flipped can be tracked using a Boolean `isFlipped` variable, which we will now describe.

### Flipping:

Since our buffer is circular, the writing head will "turn around" after writing to the last cell of its underlying array. Namely, after writing to the last cell, write pointer should point to position 0 in the array. Normally in a linear buffer we have an invariant saying the read pointer should never exceed the write pointer. But this invariant can be broken in our case of a circular buffer, since the write pointer performs the aforementioned turn around. It is useful to have an explicit boolean field (we call it `isFlipped`) indicating such a turn around has happened.

### Reading and Writing:

Reading from a buffer reads the earliest written value, and it needn't "erase data" after reading. Writing to a buffer adds a value to the buffer. Writing can simply overwrite previously read values. The status of reading and writing is tracked using two pointers `read_position` and `write_position`.

Now, the above English specification is intentionally not very precise, so our job is to formally specify and implement the methods listed below.

## 2. Example of CircularBuffer at work

Here is one example showing how Read and Write should work:

myCirBuf := Init(5);
// myCirBuf should be [ ([R,W], []), ([], []), ([], []), ([], []), ([], []) ]

res := myCirBuf.Write(1);
// myCirBuf should be [ ([R], [1]), ([W], []), ([], []), ([], []), ([], []) ]

res := myCirBuf.Write(2);
// myCirBuf should be [ ([R], [1]), ([], [2]), ([W], []), ([], []), ([], []) ]

res := myCirBuf.Write(3);
// myCirBuf should be [ ([R], [1]), ([], [2]), ([], [3]), ([W], []), ([], []) ]

succ, out := myCirBuf.Read();
// succ := true while out := 1
// myCirBuf should be [ ([], [1]), ([R], [2]), ([], [3]), ([W], []), ([], []) ]

succ, out := myCirBuf.Read();
// succ := true while out := 2
// myCirBuf should be [ ([], [1]), ([], [2]), ([R], [3]), ([W], []), ([], []) ]

succ, out := myCirBuf.Read();
// succ := true while out := 3
// myCirBuf should be [ ([], [1]), ([], [2]), ([], [3]), ([R,W], []), ([], []) ]

succ, out := myCirBuf.Read();
// succ := false while out := 0
// myCirBuf should be [ ([], [1]), ([], [2]), ([], [3]), ([R,W], []), ([], []) ]
// isEmpty(myCirBuf) should be true

## 3. Rules

Here are some rules for the final submission:

- You should NOT require more than `Valid()` for `Read` and `Write`; but it is up to you to design what goes into `Valid()`.
- You should ensure `Valid()` for both `Read` and `Write`; but you can verify this last.
- Unless you can provide strong motivation, do not change the APIs (namely the input/output parameters). As for the choice of underlying data type/data structure, you are free to pick other basic data type/default structure from Dafny
