import java.util.Random;

public class Cars implements Vehicle{
    private String name;
    private int saleBonus;
    private int repairBonus;
    private int washBonus;
    private int cost;
    private int salePrice;
    private String condition;
    private String cleanliness;

    private int min = 10000;
    private int max = 20000;

    public Cars(){
        //https://stackoverflow.com/questions/3680637/generate-a-random-double-in-a-range
        // https://www.geeksforgeeks.org/how-to-set-precision-for-double-values-in-java/

        Random r = new Random();
        cost = r.nextInt(max - min) + min;
        condition = Vehicle.getPossibleConditions().get(r.nextInt(3));
        cleanliness = Vehicle.getPossibleCleanliness().get(r.nextInt(3));

    }
    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setSaleBonus(int saleBonus) {
        this.saleBonus = saleBonus;
    }

    @Override
    public void setRepairBonus(int repairBonus) {
        this.repairBonus = repairBonus;
    }

    @Override
    public void setWashBonus(int washBonus) {
        this.washBonus = washBonus;
    }

    @Override
    public void setSalePrice(int salePrice) {
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
    public int getSaleBonus() {
        return this.saleBonus;
    }

    @Override
    public int getRepairBonus() {
        return this.repairBonus;
    }

    @Override
    public int getWashBonus() {
        return this.washBonus;
    }

    @Override
    public int getCost() {
        return this.cost;
    }

    @Override
    public int getSalePrice() {
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
