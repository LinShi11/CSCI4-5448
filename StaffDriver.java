import java.util.Random;

/**
 * This class implements the driver. Everything is the same aside from win count and injured status
 */

public class StaffDriver implements Staff{

    private String name;
    private int winCount;
    private int dailySalary;
    private int dailyBonus;
    private int totalPay;
    private int totalBonus;
    private String status;
    private int totalDaysWorked;
    private Enum.StaffType type;

    /**
     * Drivers do not quit like other Staff subclasses. 
     * However, they may be Injured in a Race activity. In that case,  
     * they leave the FNCD and are replaced by a new Driver the following day.  
     */
    private boolean injured;

    public StaffDriver(String name){
        setName(name);
        this.totalBonus = 0;
        this.dailyBonus = 0;
        this.totalPay = 0;
        this.status = "working";
        this.totalDaysWorked = 0;
        this.injured = false;
        this.winCount = 0;
        this.type = Enum.StaffType.Driver;
    }

    /**
     * All getter and setter are the same.
     *
     */

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setDailySalary(int dailySalary) {
        this.dailySalary = dailySalary;
    }

    @Override
    public void setDailyBonus(int dailyBonus){
        this.dailyBonus = dailyBonus;
    }

    @Override
    public void setTotalPay() {
        this.totalPay += dailySalary;
    }

    @Override
    public void setTotalBonus() {
        this.totalBonus+=this.dailyBonus;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public void setTotalDaysWorked() {
        this.totalDaysWorked++;
    }

    /**
     * set the win count if the driver won
     */
    public void setWinCount(){
        winCount++;
    }

    public int getWinCount(){
        return winCount;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getDailySalary() {
        return this.dailySalary;
    }

    @Override
    public int getDailyBonus() {
        return this.dailyBonus;
    }

    @Override
    public int getTotalPay() {
        return this.totalPay;
    }

    @Override
    public int getTotalBonus() {
        return this.totalBonus;
    }

    @Override
    public String getStatus() {
        return this.status;
    }

    @Override
    public int getTotalDaysWorked() {
        return this.totalDaysWorked;
    }

    @Override
    public Enum.StaffType getType() {
        return type;
    }

    /**
     * @return the injured
     */
    public boolean isInjured() {
        return injured;
    }

    /**
     * @param injured the injured to set
     */
    public void setInjured(boolean injured) {
        this.injured = injured;
        if (injured) {
            this.status = "quit";
        }else {
            this.status = "working";
        }
    }

    /**
     * use the 30% of injured after driver lost.
     * @return whether the dirver is hurt or not
     */
    public boolean selfExam(){
        Random random = new Random();
        int hurt = random.nextInt(10);
        if(hurt < 3){
            setInjured(true);
            return true;
        }
        return false;
    }
}
