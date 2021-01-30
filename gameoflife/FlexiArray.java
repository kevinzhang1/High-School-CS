package gameoflife;


public class FlexiArray implements ArrayInterface {
         protected int Arrays[];
    public FlexiArray(int size) {
        this.Arrays = new int[size];
    }
    public void set(int index, int value) {
        Arrays[index] = value;
    }
    public int get(int index) {
        return Arrays[index];
    }
    public int length() {
        return Arrays.length;
    }
}

