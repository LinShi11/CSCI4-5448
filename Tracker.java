import java.util.concurrent.Flow;

public class Tracker extends Observer{
    private int FNCDamount;
    private int employeeAmount;
    private Flow.Subscription subscription;

    public Tracker(int FNCDamount, int employeeAmount){
        this.FNCDamount = FNCDamount;
        this.employeeAmount = employeeAmount;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
    }

    @Override
    public void onNext(String item) {
        split(item);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println(throwable.getMessage());
    }

    public int getFNCDAmount(){
        return FNCDamount;
    }

    public int getEmployeeAmount(){
        return employeeAmount;
    }

    @Override
    public void onComplete(int date) {
        System.out.println("Tracker: Day " + (date));
        System.out.println("Total money earned by all Staff: $" + employeeAmount);
        System.out.println("Total money earned by FNCD: $" + FNCDamount);
    }

    public void split(String item){
        String[] arr = item.split("\\$");
        if(arr[0].equals("employee ")){
            employeeAmount += Integer.parseInt(arr[1]);
        } else{
            FNCDamount += Integer.parseInt(arr[1]);
        }
    }
}
