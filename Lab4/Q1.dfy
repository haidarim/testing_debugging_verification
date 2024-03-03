
/* 
Answer Question 1a here:

1) number of error = 1
   the error message says the postcondition might not hold on this return path

   the reason is the postcondition will not be true if both inputs are equals. 


method M(x : int, y : int) returns (a : int, b : int) 
  requires y != x
  ensures a > b;
{
  if (x > y)
   {a:= x;
    b := y;}
  else
   {a := y; 
    b := x;}
}
*/

//3)
//postconition changed to a>=b
method M1(x : int, y : int) returns (a : int, b : int) 
  ensures a >= b;
{
  if (x > y)
   {a:= x;
    b := y;}
  else
   {a := y; 
    b := x;}
}

//precondition changed inputs: x!=y
method M2(x : int, y : int) returns (a : int, b : int) 
  requires y != x
  ensures a > b;
{
  if (x > y)
   {a:= x;
    b := y;}
  else
   {a := y; 
    b := x;}
}


//statement should be changed to return a>b 
method M3(x : int, y : int) returns (a : int, b : int) 
  ensures a > b;
{
  if (x > y)
   {a:= x;
    b := y;}
  else 
   {a := y+1; 
    b := x;}
}
