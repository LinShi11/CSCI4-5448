public class Hunter implements People{
    Enum.jobType type;
    public Hunter(){
        type = Enum.jobType.Hunter;
    }
    @Override
    public int getFood() {
        return 0;
    }

    @Override
    public int getMeat() {
        return 1;
    }

    @Override
    public int getWater() {
        return 0;
    }

    @Override
    public int getFur() {
        return 1;
    }

    @Override
    public int getWood() {
        return 0;
    }

    @Override
    public int getRock() {
        return 0;
    }

    @Override
    public int getClothes() {
        return 0;
    }

    @Override
    public int getHealth() {
        return 0;
    }

    @Override
    public boolean getStatus() {
        return false;
    }

    @Override
    public void setStatus(boolean status) {

    }

    @Override
    public Enum.jobType getType() {
        return type;
    }
}
