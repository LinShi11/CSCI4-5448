package ObserverPattern;

/**
 * observer pattern. The subject interface
 */
public interface Subject {
    public void register(ObserverInterface obj);

    public void notifyObserver(String event);
}
