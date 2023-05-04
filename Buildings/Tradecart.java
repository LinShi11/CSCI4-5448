package Buildings;

import GamePlay.Enum;

/**
 * Tradecart class implements the building class. This is an singleton pattern
 */
public class Tradecart implements Building {
    private static Tradecart tradecart;
    Enum.buildingType type;
    public Tradecart(){
        type = Enum.buildingType.Tradecart;
    }

    /**
     * lazy instantiation of the object. can only have one tradecart
     * @return the tradecart object
     */
    public static Tradecart getInstance(){
        if(tradecart == null){
            tradecart = new Tradecart();
        }
        return tradecart;
    }
    /**
     * getter for type
     * @return the corresponding type
     */
    @Override
    public Enum.buildingType getType() {
        return type;
    }
}
