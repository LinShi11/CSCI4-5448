package Buildings;

import GamePlay.Enum;

/**
 * The building interface
 */
public interface Building {
    /**
     * returns teh type
     * @return the type
     */
    Enum.buildingType getType();
}
