package gameoflife;
import java.util.*;

public class GTTask extends TimerTask {
    Colony cle;
    String colonyname;
    int numbergenerations = 0;
    int numberevolvesrequested;
    private Timer timer;
    public int delay = 200;
    public GTTask(Colony cl) {
        cle=cl;
        colonyname = cl.currentcolonyname;
    }
    public void run() {
        numbergenerations += 1;
        if(numberevolvesrequested == numbergenerations) {
            timer.cancel();
            timer = null;
        } else {
            cle.evolve();
        }
    }
    public void getgoing(int numberRuns, int delay) {
        timer = new Timer();
        numberevolvesrequested = numberRuns;
        this.delay = delay;
        timer.schedule(this, delay, delay);
    }
}


