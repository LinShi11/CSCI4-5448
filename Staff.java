/**
 * The interface for staff, used for Intern, Mechanics, and Salesperson
 */
public interface Staff {
    /**
     * The signature of all functions, more details in the actual implementation comments
     *
     */
    void setName(String name);
    void setDailySalary(int dailySalary);
    void setDailyBonus(int dailyBonus);
    void setTotalPay();
    void setTotalBonus();
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
