public class StaffDriver implements Staff{

    private String name;
    private int dailySalary;
    private int dailyBonus;
    private int totalPay;
    private int totalBonus;
    private String status;
    private int totalDaysWorked;
    
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
        this.dailySalary = 200;
        this.totalPay = 0;
        this.status = "Working";
        this.totalDaysWorked = 0;
        this.injured = false;
    }

    public StaffDriver(String name, int days, int bonus, int pay){
        setName(name);
        this.totalBonus = bonus;
        this.dailyBonus = 0;
        this.dailySalary = 200;
        this.totalPay = pay;
        this.status = "Working";
        this.totalDaysWorked = days;
        this.injured = false;
    }
    
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
        this.totalPay+=this.dailySalary;
    }

    @Override
    public void setTotalBonus() {
        System.out.println(dailyBonus);
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
			this.status = "Injured/quit";
		}else {
			this.status = "Working";
		}
	}
}
