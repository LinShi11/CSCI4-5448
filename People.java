import java.util.List;

public class People{

	private int count;
	private boolean status;
	private int limit;
	
	private List<MagicItemDecorator> component;

	public int getFood() {
		return 0;
	}

	public int getMeat() {
		return 0;
	}

	public int getWater() {
		return 0;
	}

	public int getFur() {
		return 0;
	}

	public int getWood() {
		return 0;
	}

	public int getRock() {
		return 0;
	}

	public int getClothes() {
		return 0;
	}
	
	public int getHealth() {
		return 0;
	}

	public boolean getStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getLimit() {
		return limit;
	}
	
	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
