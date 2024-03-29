import java.util.*;
import java.util.function.IntBinaryOperator;

public class Set {
  private ArrayList<Integer> a;

  public Set() {
    a = new ArrayList<Integer>();
  }

  public int[] toArray() {
    int[] ia = new int[a.size()];
    for (int i = 0; i < ia.length; i++) {
      ia[i] = a.get(i);
    }
    return ia;
  }

  public void insert(int x) { 
    for (int i = 0; i < a.size(); i++) {
      if (a.get(i) > x) {
        a.add(i, x);
        return;
      } else {
        if (a.get(i) == x) {
          return;
        }
      }
    }
    a.add(x);
  }

  public boolean member(int x) {
    for (int i = 0; i < a.size(); i++) {
      if (a.get(i) > x) {
        return false;
      } else {
        if (a.get(i) == x) {
          return true;
        }
      }
    }
    return false;
  }
  
  public void intersect(Set s)
  {
    int counter = 0;
    for(int i = 0, j = 0 ; i < a.size() && j < s.a.size(); )
    {
      if (a.get(i).equals(s.a.get(j)))
      {
        i++;
        j++;
        counter++;
      }
      else
      {
        if(a.get(i) < s.a.get(j))
        {
          a.remove(i);
          //i++;
        }
        else
        {
          j++;
        }
      }
    }
    while (counter<this.a.size()) {
        this.a.remove(this.a.size()-1);
    }
  }

  // Try with:
  //   (a, b) -> a + b;
  //   (a, b) -> a - b;
  public boolean distinctClosed(IntBinaryOperator f) {
    int vi,vj;
    for (int i = 0; i < a.size(); i++) {
      for (int j = i; j < a.size(); j++) { // alt: j = 0
        vi = a.get(i);
        vj = a.get(j);
        if (!(member(f.applyAsInt(vi, vj)) || vi == vj) || !(member(f.applyAsInt(vj, vi)) || vj == vi)) return false;
      }
    }
    return true;
  }
}
