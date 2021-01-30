package gameoflife;

public class Array2D {
    int rownumber;
        int columnnumber;
        ArrayInterface ai;
    public Array2D(int numberRows, int numberCols, boolean isSparse) {        
       
        this.rownumber = numberRows;
        this.columnnumber = numberCols;
        if(isSparse) {
           ai = new SparseArray(numberRows * numberCols);
        }
        else {
            ai = new FlexiArray(numberRows * numberCols);
        }
    }
    public void set(int r, int c, int value) {
        int index = index(r, c);
        if(index > ai.length()-1) {
            System.exit(0);
        } else {
        ai.set(index, value);
        }
    }
    
    private int index(int row, int col) {
        int indexs = row * columnnumber + col;
       return indexs; 
    }
    public int get(int r, int c) {
        int index = index(r, c);
        int value = 0;
        if(index > ai.length() - 1) {
            System.exit(0);
        } else {
            value = ai.get(index);
        }
        return value;
    }
    public int numberRows() {
        return rownumber;
    }
    public int numberCols() {
        return columnnumber;
    }
}
