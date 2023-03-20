package project3;

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
        FNCD fncd = new FNCD();
        fncd.simulation();
    }
}
