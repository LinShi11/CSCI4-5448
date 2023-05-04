package GamePlaySupport;

public interface Subject {
    public void register(ObserverInterface obj);

    public void unregister(ObserverInterface obj);


    public void notifyObserver(String event);
}
