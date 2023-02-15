public interface Staff {
    void setName(String name);
    void setDailySalary(int dailySalary);
    void setDailyBonus(int dailyBonus);
    void setTotalPay(int totalPay);
    void setTotalBonus(int totalBonus);
    void setStatus(String status);
    void setTotalDaysWorked();

    String getName();
    int getDailySalary();
    int getDailyBonus();
    int getTotalPay();
    int getTotalBonus();
    String getStatus();
    int getTotalDaysWorked();

}
