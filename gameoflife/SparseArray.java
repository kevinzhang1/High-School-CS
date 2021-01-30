package gameoflife;
import java.util.*;


public class SparseArray implements ArrayInterface {
       int size = 0;
       HashMap<Integer, Integer> sparsearray = new HashMap<Integer, Integer>();
    public SparseArray(int size) {
        this.size = size;
}
    public void set(int index, int value) {
      sparsearray.put(index, value);
        
    }
    public int get(int index) {
        if(sparsearray.get(index)==null) {
            return 0;
        } else {
            return sparsearray.get(index);
        }
    }
    public int length() {
        return this.size;
    }
    
    public int numberelements() {
        return sparsearray.size();
    }
    
}
