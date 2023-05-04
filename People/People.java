package People;

import GamePlay.Enum;

/**
 * The people interface, It will have food, meat, water, fur, wood, rock, clothes, health, defense, and gold
 */
public interface People{

	int getFood();

	int getMeat();

	int getWater();

	int getFur();

	int getWood();

	int getRock();

	int getClothes();
	
	int getHealth();
	int getDefense();
	int getGold();

	Enum.jobType getType();

}
