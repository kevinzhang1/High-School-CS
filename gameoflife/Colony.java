package gameoflife;
import java.util.*;

public class Colony {

    String currentcolonyname;
    int currentcolonysize;
    Array2D a2D, A2D;
    GameOfLife gl;
    HashMap<String, Colony> mapping = new HashMap();
    HashMap<Colony, GraphicalFrame> mapper = new HashMap();
    HashMap<Colony, GTTask> hashmap = new HashMap();
    
    public void userinterface() {
        Scanner scanner = new Scanner(System.in);
        String condition;
    do {
        System.out.print("Option: ");    
        condition = scanner.next();
        String input = scanner.nextLine();
        switch(condition) {
        case "h":
            help();
            break;
        case "i":
            for(String n : mapping.keySet()) {
            Colony cl = mapping.get(n);  
            if(n.equals(currentcolonyname)) 
                System.out.println("* " + n + " size: " + cl.currentcolonysize + " livingcells: " + cl.getNumberLivingCells());
            else    System.out.println(n + " " + cl.currentcolonysize + " livingcells: " + cl.getNumberLivingCells());
    }
            break;
        case "a":
            input = input.substring(1);
            String inputs[] = input.split(" ");
            setCellAlive(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]));
            break;
        case "d":
           input = input.substring(1);
            String inputss[] = input.split(" ");
            setCellDead(Integer.parseInt(inputss[0]), Integer.parseInt(inputss[1]));
            break;
        case "e":
            int inputsss =Integer.parseInt(input.substring(1));
            evolvecolony(inputsss);
            break;
        case "r":
            resetColony();
            break;
        case "s":
            
            break;
        case "p":
            input = input.substring(1);
            setpause(Integer.parseInt(input));
            break;
        case "c":
            input = input.substring(1);
            String[] conditions = input.split(" ");
            String name = conditions[0];
            int size = Integer.parseInt(conditions[1]);
            Colony cle = new Colony(size, name);
            mapping.put(name, cle);
            mapper.put(cle, new GraphicalFrame(cle));
            hashmap.put(cle, new GTTask(cle));
            a2D = cle.a2D;
            currentcolonysize = cle.currentcolonysize;
            currentcolonyname = cle.currentcolonyname;
            break;
        case "u":
            input = input.substring(1);
            Colony clee = mapping.get(input);
            a2D = clee.a2D;
            currentcolonysize = clee.currentcolonysize;
            currentcolonyname = clee.currentcolonyname;
            break;
    }
        
    } while((!"q".equals(condition)));
    }
  
    private void evolvecolony(int numberruns) {
        Colony cl = mapping.get(currentcolonyname);
        GTTask task = hashmap.get(cl);
        task.getgoing(numberruns, task.delay);
        task.numberevolvesrequested = numberruns;
        System.out.println(task.numbergenerations);
    }
    private void setpause(int seconds) {

         for(Colony cl : hashmap.keySet()) {
            GTTask task = hashmap.get(cl);  
            task.delay = seconds;
         }
    }
    public Colony(int size, String name) {
         a2D = new Array2D(size, size, true);
        currentcolonysize = size;
        currentcolonyname = name;
    }
    public int getColonySize() {
        return currentcolonysize;
    }
    public String getName() {
        return currentcolonyname;
    }
    public int getNumberLivingCells() {
        int value = 0;
        for(int r = 0; r < currentcolonysize; r++) {
        for(int c = 0; c < currentcolonysize; c++) {
            if(a2D.get(r, c) == 1) {
                value = value + 1;
            }
        }
        }
        return value;
    }
    public void resetColony() {
        for(int r = 0; r < currentcolonysize; r++) {
        for(int c = 0; c < currentcolonysize; c++) {
            a2D.set(r, c, 0);
        }
        }
    }
    public void setCellAlive(int row, int col) {
        a2D.set(row, col, 1);
    }
    public void setCellDead(int row, int col) {
        a2D.set(row, col, 0);
    }
    public final boolean isCellAlive(int row, int col) {
        if(a2D.get(row, col) == 1) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public int GetCells(int Row, int Col){
        int livingcells = 0;
        if(isCellAlive(Row -1, Col - 1)) {
            livingcells = livingcells + 1;
        }
         if(isCellAlive(Row, Col - 1)) {
             livingcells = livingcells + 1;
         }
         if(isCellAlive(Row -1, Col + 1)) {
             livingcells = livingcells + 1;
         }
         if(isCellAlive(Row +1, Col)) {
             livingcells = livingcells + 1;
         }
         if(isCellAlive(Row +1, Col + 1)) {
             livingcells = livingcells + 1;
         }
         if(isCellAlive(Row, Col + 1)) {
             livingcells = livingcells + 1;
         }
         if(isCellAlive(Row +1, Col - 1)) {
             livingcells = livingcells + 1;
         }
         if(isCellAlive(Row -1, Col)) {
             livingcells = livingcells + 1;
         }
        return livingcells;
    }
    public void killouterframecells() {
        for(int r = 0;r != 11; r++) {
            A2D.set(r, 1, 0);
        }
        for(int c = 0;c != 11; c++) {
            A2D.set(1, c, 0);
        }
        for(int r = 0;r != 11; r++) {
            A2D.set(r, currentcolonysize+1, 0);
        }
        for(int c = 0;c != 11; c++) {
            A2D.set(currentcolonysize + 1, c, 0);
        }
    }
    
    
    public void evolve() {
        A2D = new Array2D(currentcolonysize + 2, currentcolonysize + 2, true);
        for(int r= 0;r< currentcolonysize-2;r++) {
            for(int c = 0;c<currentcolonysize-2;c++) {
                int livingcells = GetCells(r,c);
                switch(livingcells) {
                    case 0:
                        A2D.set(r, c, 0);
                        break;
                    case 1:
                        A2D.set(r, c, 0); 
                        break;
                    case 2:
                        if(isCellAlive(r, c)) {
                            A2D.set(r, c, 1);
                        } else {
                            A2D.set(r, c, 0);
                        }
                        break;
                    case 3:
                        if(isCellAlive(r, c)) {
                        A2D.set(r, c, 1);
                        } else {
                            A2D.set(r, c, 1);
                        }
                        break;
                    case 4:
                        A2D.set(r, c, 0);
                        break;
                    case 5:
                        A2D.set(r, c, 0);
                        break;
                    case 6:
                        A2D.set(r, c, 0);
                        break;
                    case 7:
                        A2D.set(r, c, 0);
                        break;
                    case 8:
                        A2D.set(r, c, 0);
                        break;
                  
                }                
            }
        }
 for(int r = 0; r < currentcolonysize; r++) {
        for(int c = 0; c < currentcolonysize; c++) {
            if(A2D.get(r, c)==1) {
                setCellAlive(r, c);
            } else {
                setCellDead(r, c);
            }
        }
        }
    }
    public String toString() {
        String colony = "";
        for(int r= 0;r< currentcolonysize;r++) {
            for(int c = 0;c<currentcolonysize;c++) {
                if(a2D.get(r, c) == 1) {
                 colony = colony + "*";
                } else {
                    colony = colony + "0";
                }
            }
            colony = colony + "\n";
        }
        return colony;
    }
    private void help() {
        System.out.println("q - quit the game");
        System.out.println("h - print this help table of options");
        System.out.println("i - basic information report");
        System.out.println("a xcoor ycoor - set the cell at position (xcoor, ycoor) to be alive");
        System.out.println("d xcoor ycoor - set the cell at position (xcoor, ycoor) to be dead");
        System.out.println("e numGen - evolve the colony by numGen generations");
        System.out.println("r - reset the colony by setting all cells to be dead");
        System.out.println("s - toggle silent evolution");
        System.out.println("p numMilli - set the pause between evolutions to be numMilli milliseconds");
        System.out.println("c name size - create a new colony with a given name and a given size");
        System.out.println("u n - use colony number n");
    }
}
