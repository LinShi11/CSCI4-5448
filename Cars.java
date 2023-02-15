import java.util.Random;

public class Cars implements Vehicle{
    private String name;
    private double saleBonus;
    private double repairBonus;
    private double washBonus;
    private double cost;
    private double salePrice;
    private String condition;
    private String cleanliness;

    private int min = 10000;
    private int max = 20000;

    public Cars(){
        //https://stackoverflow.com/questions/3680637/generate-a-random-double-in-a-range
        // https://www.geeksforgeeks.org/how-to-set-precision-for-double-values-in-java/

        Random r = new Random();
        cost = Math.round((min + (max - min) * r.nextDouble()) * 100)/ 100.0;
        condition = Vehicle.getPossibleConditions().get(r.nextInt(3));
        cleanliness = Vehicle.getPossibleCleanliness().get(r.nextInt(3));

    }
    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setSaleBonus(double saleBonus) {
        this.saleBonus = saleBonus;
    }

    @Override
    public void setRepairBonus(double repairBonus) {
        this.repairBonus = repairBonus;
    }

    @Override
    public void setWashBonus(double washBonus) {
        this.washBonus = washBonus;
    }

    @Override
    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    @Override
    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public void setCleanliness(String cleanliness) {
        this.cleanliness = cleanliness;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getSaleBonus() {
        return this.saleBonus;
    }

    @Override
    public double getRepairBonus() {
        return this.repairBonus;
    }

    @Override
    public double getWashBonus() {
        return this.washBonus;
    }

    @Override
    public double getCost() {
        return this.cost;
    }

    @Override
    public double getSalePrice() {
        return this.salePrice;
    }

    @Override
    public String getCondition() {
        return this.condition;
    }

    @Override
    public String getCleanliness() {
        return this.cleanliness;
    }
}
