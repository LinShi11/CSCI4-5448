import java.util.concurrent.Flow;


//https://www.tutorialspoint.com/how-to-implement-reactive-streams-using-flow-api-in-java-9
//https://blog.softwaremill.com/how-not-to-use-reactive-streams-in-java-9-7a39ea9c2cb3
public class Observer implements Flow.Subscriber<String>{
    private Flow.Subscription subscription;
    protected int date;

    public void setDate(int date){
        this.date = date;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(String item) { //observer class will define 3 concrete classes
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println(throwable.getMessage());
    }

    @Override
    public void onComplete() {
        subscription.cancel();
    }
}
