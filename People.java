import java.util.List;

public interface People{

	int getFood();

	int getMeat();

	int getWater();

	int getFur();

	int getWood();

	int getRock();

	int getClothes();
	
	int getHealth();

	boolean getStatus();
	
	void setStatus(boolean status);

	int getLimit();
	
	void setLimit(int limit);

	int getCount();

	void setCount(int count);

	Enum.jobType getType();

}
