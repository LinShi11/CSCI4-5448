
public class BuyNormalInventoryItem implements Command {

	private Vehicle chosenVehicle;
	
	public BuyNormalInventoryItem(Vehicle chosenVehicle) {
		this.chosenVehicle = chosenVehicle;
	}

	@Override
	public void execute() {

	}

	/**
	 * @return the chosenVehicle
	 */
	public Vehicle getChosenVehicle() {
		return chosenVehicle;
	}

	
}
