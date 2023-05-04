package ObserverPattern;

import java.util.ArrayList;

public class Logger implements ObserverInterface {
    ArrayList<String> events;
    private static Logger logger;
    private Logger(){
        events = new ArrayList<>();
    }
    public static Logger getInstance(){
        if(logger == null){
            logger = new Logger();
        }
        return logger;
    }
    @Override
    public void update(String event) {
        events.add(event);
    }

    public ArrayList<String> getEvents(){
        return events;
    }

}
