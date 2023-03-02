public class SaleDecorator implements Vehicle {
    protected Vehicle car;

    public SaleDecorator(Vehicle car) {
        this.car = car;
    }

    @Override
    public void setName(String name) {
        car.setName(name);
    }

    @Override
    public void setBrand() {
        car.setBrand();
    }

    @Override
    public void setCost() {
        car.setCost();
    }

    @Override
    public void setSalePrice(double percentage) {
        car.setSalePrice(percentage);
    }

    public double getPercent(){
        return this.car.getPercent();
    }

    @Override
    public void setSaleBonus(int saleBonus) {
        car.setSaleBonus(saleBonus);
    }

    @Override
    public void setRepairBonus(int repairBonus) {
        car.setRepairBonus(repairBonus);
    }

    @Override
    public void setWashBonus(int washBonus) {
        car.setWashBonus(washBonus);
    }

    @Override
    public void setCondition(String condition) {
        car.setCondition(condition);
    }

    @Override
    public void setCleanliness(String cleanliness) {
        car.setCleanliness(cleanliness);
    }

    @Override
    public void setStatus(String status) {
        car.setStatus(status);
    }

    @Override
    public void setWinCount() {
        car.setWinCount();
    }

    @Override
    public String getName() {
        return car.getName();
    }

    @Override
    public String getBrand() {
        return car.getBrand();
    }

    @Override
    public int getSaleBonus() {
        return car.getSaleBonus();
    }

    @Override
    public int getRepairBonus() {
        return car.getRepairBonus();
    }

    @Override
    public int getWashBonus(int level) {
        return car.getWashBonus(level);
    }

    @Override
    public int getCost() {
        return car.getCost();
    }

    @Override
    public int getSalePrice() {
        return car.getSalePrice();
    }

    @Override
    public String getCondition() {
        return car.getCondition();
    }

    @Override
    public String getCleanliness() {
        return car.getCleanliness();
    }

    @Override
    public String getStatus() {
        return car.getStatus();
    }

    @Override
    public String getType() {
        return car.getType();
    }

    @Override
    public void printAction() {

    }
}
