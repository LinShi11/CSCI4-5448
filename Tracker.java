import java.util.concurrent.Flow;

public class Tracker extends Observer{
    private int FNCDamount;
    private int employeeAmount;
    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
    }

    @Override
    public void onNext(String item) {
        System.out.println("Tracker");
        System.out.println(item);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println(throwable.getMessage());
    }

    @Override
    public void onComplete() {
        System.out.println("Done");
    }
}
