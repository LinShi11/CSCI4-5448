package project4;

import java.util.concurrent.Flow;

/**
 * This class extends from the observer (part of the Observer pattern)
 * It takes the text for each time, parse it and store it. the final calculation will be made by the end of the day
 * Additionally, this represents a Singleton pattern. In this case, it is a lazy instantiation. Where we do not instantiate the tracker until it is needed
 */
public class Tracker extends Observer{
    private int FNCDamount;
    private int employeeAmount;
    private Flow.Subscription subscription;
    private static Tracker uniqueTracker;
    private int count;

    /**
     * constructor
     * @param FNCDamount: the FNCD amount from the previous days
     * @param employeeAmount: the employee amount from the previous days
     */
    public Tracker(int FNCDamount, int employeeAmount){
        this.FNCDamount = FNCDamount;
        this.employeeAmount = employeeAmount;
        this.count = 0;
    }

    /**
     * lazy instantiation. When the function is first called, we will check to see if there is a Tracker, if not, we will create one
     * @param FNCDamount: the current amount for both FNCD
     * @param employeeAmount: the current amount for both FNCD employee
     * @return The tracker
     */
    public static Tracker getInstance(int FNCDamount, int employeeAmount){
        if(uniqueTracker == null){
            uniqueTracker = new Tracker(FNCDamount, employeeAmount);
        }
        return uniqueTracker;
    }

    /**
     * part of override
     * @param subscription a new subscription
     */
    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
    }

    /**
     * parse each item
     * @param item the item
     */
    @Override
    public void onNext(String item) {
        split(item);
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
     * getter for current FNCD amount
     * @return FNCD amount
     */
    public int getFNCDAmount(){
        return FNCDamount;
    }

    /**
     * getter for current employee amount
     * @return employee amount
     */
    public int getEmployeeAmount(){
        return employeeAmount;
    }

    /**
     * prints the final status when closing it for each day
     * @param date: the current date
     */
    @Override
    public void onComplete(int date) {
        count ++;
        if(count % 2 == 0) {
            System.out.println("Tracker: Day " + (date) + " (from both locations)");
            System.out.println("Total money earned by all Staff: $" + employeeAmount);
            System.out.println("Total money earned by both FNCD: $" + FNCDamount);
        }
    }

    /**
     * split function that splits the string into two parts
     * all employee income will be in employee $amount
     * all FNCD income will be with the announcement for selling a car
     * @param item: the string to parse
     */
    public void split(String item){
        String[] arr = item.split("\\$");
        if(arr[0].equals("employee ")){
            employeeAmount += Integer.parseInt(arr[1]);
        } else{
            FNCDamount += Integer.parseInt(arr[1]);
        }
    }
}
