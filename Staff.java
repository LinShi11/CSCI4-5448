public interface Staff {
    void setName(String name);
    void setDailySalary(double dailySalary);
    void setDailyBonus(double dailyBonus);
    void setTotalPay(double totalPay);
    void setTotalBonus(double totalBonus);
    void setStatus(String status);
    void setTotalDaysWorked(int days);

    String getName();
    double getDailySalary();
    double getDailyBonus();
    double getTotalPay();
    double getTotalBonus();
    String getStatus();
    int getTotalDaysWorked();

}
