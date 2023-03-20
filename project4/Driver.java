package project4;

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
 * This is Project 4 for CSCI 4/5448 OOAD
 *
 * This program is a simulation of the Friendly Neighborhood Car Dealership (FNCD).
 * The driver class contains both FNCDs, we used CyclicBarrier for threading.
 */
public class Driver {
    public static void main(String[] args){
    	
    	// threading purposes
    	CyclicBarrier barrier = new CyclicBarrier(2);

		// create the two FNCD
    	FNCD north = new FNCD("North", barrier, true);
    	FNCD south = new FNCD("South", barrier, true);

		// create the thread
    	Thread northThread = new FNCDThead(north);    	
    	Thread southThread = new FNCDThead(south);

		//start both north and south threads
    	northThread.start();
    	southThread.start();
    	
		// Sleep for 1 sec so other threads can work too
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
		
		 //The join method is called on each thread he calling thread (in this case, the main thread) 
		 // to pause its execution and wait for the target thread (either "northThread" or "southThread") to complete before resuming its own execution.

		 
    	try {
			northThread.join();
			southThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// gets all the information from both FNCD for graphing
		ArrayList<Integer> northCount = north.getCount();
		ArrayList<Integer> northEmployee = north.getEmployeeMoney();
		ArrayList<Integer> northFNCD = north.getFNCDMoney();

		ArrayList<Integer> southCount = south.getCount();
		ArrayList<Integer> southEmployee = south.getEmployeeMoney();
		ArrayList<Integer> southFNCD = south.getFNCDMoney();

		// draws both charts
		new JFreeChartGraph(northCount, northEmployee, northFNCD, southCount, southEmployee, southFNCD).setVisible(true);
    	new JFreeChartGraph(northCount, southCount).setVisible(true);
	}
}
/**
  * FNCDThread class is a wrapper class for the FNCD class which is responsible for running the simulation method
  of the FNCD instance which is passed in as a parameter when the thread starts.
  */

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


