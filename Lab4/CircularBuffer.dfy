// class CircularMemory
// This class implements a cicular buffer with with 2 int typed pointers
class CircularMemory
  {
  var cells : array<int>
  var read_position : int //front
  var write_position : int //rear
  var isFlipped : bool // whether


  constructor Init(cap : int)
    requires cap > 0
    ensures cells.Length == cap && read_position == 0 && write_position == 0 && !isFlipped
  {
    cells := new int[cap];
    read_position, write_position :=0, 0;
    isFlipped := false;
  }


  // valid is the class invariant
  predicate Valid()
    reads this
  {
    // Think of some constraints on:
    // 1. cells.Length?
    // 2. write_position?
    // 3. read_position?

    cells.Length>0 &&
    read_position>=0 && read_position < cells.Length &&
    write_position >=0 && write_position < cells.Length
  }

  // A predicate indicating no more Read available
  // obs: med detta signatur man kan använda predicate:n bara i specification, men om man säger predicate method man kan använda då även i kod body dvs i {S}
  predicate isEmpty()
    //reads `isFlipped, `read_position, `write_position
    reads this
  {
    !isFlipped && read_position == write_position
  }


  //A predicate indicating no more Write should be allowed
  predicate isFull()
    reads `isFlipped, `read_position, `write_position
  {
    isFlipped && read_position == write_position
  }

  method Read() returns (isSuccess : bool, content : int)
    //reads `isFlipped, `read_position, `write_position
    modifies `read_position, `isFlipped
    //modifies this
    requires Valid()
    ensures  Valid()
    //ensures isSuccess ==> !isEmpty()
    ensures  isSuccess ==>
               !isEmpty() && content == old(cells[read_position]) &&
               read_position == (old(read_position)+1) % cells.Length
    ensures !isSuccess ==>
              isEmpty() && content == 0 && read_position == old(read_position)

  {
    if(!isFlipped && read_position == write_position)
    {
      isSuccess := false;
      content:= 0; // dummy value
    }
    else
    {
      isSuccess:= true;
      content:= cells[read_position];
      read_position:= (read_position+1)%cells.Length;

      //check isFlipped and update it if necessary
      if(!isFlipped && read_position == write_position) //or isEmpy()
      {
        isFlipped := true;
      }
    }
  }

  method Write(input : int) returns (isSuccess : bool)
    //reads `isFlipped, `read_position, `write_position
    modifies `write_position, `isFlipped, cells
    //modifies this
    requires Valid()
    ensures  Valid()
    ensures  isSuccess ==> !isEmpty() && write_position == old(write_position +1)%cells.Length
    ensures !isSuccess ==> isFull() && write_position == old(write_position)
  {
    if(isFlipped && read_position == write_position)
    {
      isSuccess:=false;
    }
    else
    {
      isSuccess:=true;
      cells[write_position] := input;
      write_position:= (write_position+1)% cells.Length;

      //check isFlipped and update it if necessary
      if(!isFlipped && read_position == write_position)
      {
        isFlipped:=true;
      }
    }
  }


  // Your finished Lab 3 code and predicates...

  // Question 3 for Lab 4
  method DoubleCapacity()
    modifies `cells
    requires Valid()
    ensures Valid()
    ensures cells.Length == 2 * old(cells.Length)
    ensures read_position == old(read_position)
    ensures write_position == old(write_position)
    ensures forall j : int :: 0 <= j < old(cells.Length) ==> cells[j] == old(cells[j])
    ensures forall j : int :: old(cells.Length) <= j < cells.Length ==> cells[j] == 0 // en till loop
  {
    // one or more loops to double the capacity of cells
    // the most important part is the loop invariants!
    //var oldCells := cells;
    var oldCap := cells.Length;
    var newCap := 2 * oldCap;

    var tmp:array<int>:= cells;

    // Copy elements from the old array to the new array
    var i: int := 0;
    cells:= new int[newCap];
    while i < oldCap
      modifies cells
      invariant Valid()
      invariant 0 <= i <= oldCap
      invariant cells.Length == 2*oldCap
      invariant forall j: int :: 0 <= j < i ==> cells[j] == tmp[j]
    {
      cells[i] := tmp[i];
      i := i + 1;
    }
    
    while oldCap<newCap
          modifies cells 
    	  invariant Valid()
          invariant i<=oldCap<=newCap
          //invariant cells.Length == 2*oldCap
          invariant forall j: int :: 0 <= j < i ==> cells[j] == tmp[j]
          invariant forall j: int :: i <= j < oldCap ==> cells[j] == 0
        {
          cells[oldCap]:= 0;
          oldCap:= oldCap+1;
      }
    
  }
}
