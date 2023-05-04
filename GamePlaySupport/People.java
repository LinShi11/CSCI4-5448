package GamePlaySupport;

import GamePlay.Enum;

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
