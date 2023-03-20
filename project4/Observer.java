package project4;

import java.util.concurrent.Flow;

/**
 * This class implements the subscriber. (part of the Observer pattern)
 *
 * We looked at following code examples for inspiration but did not use it completely
 * https://blog.softwaremill.com/how-not-to-use-reactive-streams-in-java-9-7a39ea9c2cb3
 * https://www.tutorialspoint.com/how-to-implement-reactive-streams-using-flow-api-in-java-9
 */
public class Observer implements Flow.Subscriber<String>{
    private Flow.Subscription subscription;

    /**
     * part of override
     * @param subscription a new subscription
     */
    @Override
    public void onSubscribe(Flow.Subscription subscription) { //observer class will define 3 concrete classes
        this.subscription = subscription;
        subscription.request(1);
    }

    /**
     * part of override
     * @param item the item
     */
    @Override
    public void onNext(String item) {
        System.out.println(item);
    }

    /**
     * used for logger so the correct file will be generated.
     * @param item the item to add
     * @param date the date for file
     */
    public void onNext(String item, int date, String name) {
        subscription.request(1);
    }

    /**
     * part of override
     * @param throwable the exception
     */
    @Override
    public void onError(Throwable throwable) {
        System.out.println(throwable.getMessage());
    }

    /**
     * complete it
     */
    @Override
    public void onComplete() {
        subscription.cancel();
    }

    /**
     * used for tracker, so the correct format can be displayed
     * @param date: the current date
     */
    public void onComplete(int date){
        subscription.cancel();
    }
}
