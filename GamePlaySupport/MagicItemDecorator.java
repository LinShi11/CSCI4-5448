package GamePlaySupport;

import GamePlay.Enum;

public class MagicItemDecorator implements People {
	protected People person;
	public MagicItemDecorator(People person){
		this.person = person;
	}
	
	public int getFood() {
		return person.getFood();
	}

	public int getMeat() {
		return person.getMeat();
	}

	public int getWater() {
		return person.getWater();
	}

	public int getFur() {
		return person.getFur();
	}

	public int getWood() {
		return person.getWood();
	}

	public int getRock() {
		return person.getRock();
	}

	public int getClothes() {
		return person.getClothes();
	}

	@Override
	public int getHealth() {
		return person.getHealth();
	}
	@Override
	public int getDefense() {
		return person.getDefense();
	}

	@Override
	public int getGold() {
		return person.getGold();
	}

	@Override
	public Enum.jobType getType() {
		return null;
	}

}
