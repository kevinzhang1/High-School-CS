package gameoflife;
import java.util.*;

public class GameOfLife {
    public HashMap <String, Colony> mappings = new HashMap();    
      
public void colonytest() {
    Colony cle = new Colony(10, "test colony");
    mappings.put("test colony", cle);
    cle.userinterface();
}


    public static void main(String[] args) {
GameOfLife gl = new GameOfLife();
gl.colonytest();
    }
    
}

