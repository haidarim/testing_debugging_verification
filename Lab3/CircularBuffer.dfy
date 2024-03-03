// class CircularMemory
// This class implements a cicular buffer with with 2 int typed pointers
class CircularMemory
{
  var cells : array<int>;
  var read_position : int; //front
  var write_position : int; //rear
  var isFlipped : bool; // whether 


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


}
