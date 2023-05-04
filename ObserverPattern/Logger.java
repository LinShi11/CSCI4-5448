package ObserverPattern;

import java.util.ArrayList;

/**
 * singleton pattern: only one instance of logger
 * part of the observer pattern: stores all the data when the info is announced.
 */
public class Logger implements ObserverInterface {
    ArrayList<String> events;
    private static Logger logger;
    private Logger(){
        events = new ArrayList<>();
    }

    /**
     * singleton pattern, only one instance is created
     * @return: the instance of logger
     */
    public static Logger getInstance(){
        if(logger == null){
            logger = new Logger();
        }
        return logger;
    }

    /**
     * add the message to the arraylist
     * @param event: the new message
     */
    @Override
    public void update(String event) {
        events.add(event);
    }

    /**
     * arraylist of all the events
     * @return arraylist of all the events
     */
    public ArrayList<String> getEvents(){
        return events;
    }

}
