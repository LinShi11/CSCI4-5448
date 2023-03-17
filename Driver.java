import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.CyclicBarrier;

/**
 * @author Lin Shi
 * @author Anuragini Sinha
 * This is Project 2 for CSCI 4/5448 OOAD
 *
 * This program is a simulation of the Friendly Neighborhood Car Dealership (FNCD).
 * The driver class only holds an instance of fncd and calls simulation.
 */
public class Driver {
	
    public static void main(String[] args){   	    	
    	
    	
    	CyclicBarrier barrier = new CyclicBarrier(2);
		Logger logger = new Logger();
		Tracker tracker = new Tracker(0,0);
    	
    	FNCD north = new FNCD("North", barrier, true);
    	FNCD south = new FNCD("South", barrier, true);
    	
    	Thread northThread = new FNCDThead(north);    	
    	Thread southThread = new FNCDThead(south);
    	
    	northThread.start();
    	southThread.start();
    	
    	try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
    	
    	CommandLineInterface inf = new CommandLineInterface(
    			 Arrays.asList(new FNCD[] {north, south}));
    	    	
    	try {
			inf.execute(new BufferedReader(new InputStreamReader(System.in)));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	
    	//wait for complete
    	try {
			northThread.join();
			southThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ArrayList<Integer> northCount = north.getCount();
		ArrayList<Integer> northEmployee = north.getEmployeeMoney();
		ArrayList<Integer> northFNCD = north.getFNCDMoney();

		ArrayList<Integer> southCount = south.getCount();
		ArrayList<Integer> southEmployee = south.getEmployeeMoney();
		ArrayList<Integer> southFNCD = south.getFNCDMoney();

		new JFreeChartGraph(northCount, northEmployee, northFNCD, southCount, southEmployee, southFNCD).setVisible(true);
    	new JFreeChartGraph(northCount, southCount).setVisible(true);
	}
}

class FNCDThead extends Thread{
	
	private FNCD fncd;
	 
	/**
	 * @param barrier
	 */
	public FNCDThead(FNCD fncd) {
		this.fncd = fncd;
	}

	public void run() {
        fncd.simulation();
	}
	
}


