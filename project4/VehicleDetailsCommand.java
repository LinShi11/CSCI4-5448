package project4;

public class VehicleDetailsCommand implements Command {

	private Vehicle chosenVehicle;
	
	/**
	 * @param chosenVehicle
	 */
	public VehicleDetailsCommand(Vehicle chosenVehicle) {
		this.chosenVehicle = chosenVehicle;
	}


	@Override
	public void execute() {
		Helper.printVehicle(chosenVehicle);
	}

}
