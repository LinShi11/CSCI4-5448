public class Miner implements People{
    Enum.jobType type;
    public Miner(){
        type = Enum.jobType.Miner;
    }
    @Override
    public int getFood() {
        return 0;
    }

    @Override
    public int getMeat() {
        return 0;
    }

    @Override
    public int getWater() {
        return 0;
    }

    @Override
    public int getFur() {
        return 0;
    }

    @Override
    public int getWood() {
        return 0;
    }

    @Override
    public int getRock() {
        return 1;
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
