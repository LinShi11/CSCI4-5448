public class Interns implements Staff {
    private String name;
    private double dailySalary;
    private double dailyBonus;
    private double totalPay;
    private double totalBonus;
    private String status;
    private int totalDaysWorked;

    public Interns(String name){
        setName(name);
        this.totalBonus = 0;
        this.dailyBonus = 0;
        this.dailySalary = 0;
        this.totalPay = 0;
        this.status = "Working";
        this.totalDaysWorked = 0;
    }


    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setDailySalary(double dailySalary) {
        this.dailySalary = dailySalary;
    }

    @Override
    public void setDailyBonus(double dailyBonus) {
        this.dailyBonus = dailyBonus;
    }

    @Override
    public void setTotalPay(double totalPay) {
        this.totalPay = totalPay;
    }

    @Override
    public void setTotalBonus(double totalBonus) {
        this.totalBonus = totalBonus;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public void setTotalDaysWorked(int days) {
        this.totalDaysWorked = days;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getDailySalary() {
        return this.dailySalary;
    }

    @Override
    public double getDailyBonus() {
        return this.dailyBonus;
    }

    @Override
    public double getTotalPay() {
        return this.totalPay;
    }

    @Override
    public double getTotalBonus() {
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
}

