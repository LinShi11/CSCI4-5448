import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class CommandLineInterface {

	private List<FNCD> FNCDs;
	
	/**
	 * @param fNCDs
	 */
	public CommandLineInterface(List<FNCD> fNCDs) {
		FNCDs = fNCDs;
	}

	/**
	 * input
	 */
	private BufferedReader input;
	
	//execute
	public void execute(BufferedReader input) throws IOException {
		
		FNCD currentFNCD = null; //chosen FNCD
	    this.input = input;
	    
	    //chosen vehicle
	    Vehicle chosenVehicle = null;
		
		// display menu and read selection
		int selection = menu();

		// run until user quits
		while (selection != 0) {

			switch (selection) {
			case 1:
			{
				System.out.print("Please choose 1 for North or other for South: ");
		    	
		    	if (input.readLine().equals("1")) {
		    		currentFNCD = getFNCD("North");
		    	}else {
		    		currentFNCD = getFNCD("South");
		    	}
			}
			break;
			case 2:
			{
				if (currentFNCD == null) {
					System.out.println("Please chooose FNCD (option 1)");
				}else {

					synchronized (currentFNCD.getCommandQueue()) {			
						currentFNCD.getCommandQueue().add(new SalespersonNameCommand());
						currentFNCD.getCommandQueue().notifyAll();	
					}
					
				}
			}
			break;
			case 3:
			{
				if (currentFNCD == null) {
					System.out.println("Please chooose FNCD (option 1)");
				}else {
					currentFNCD.getCommandQueue().add(new TimeCommand());
					
					synchronized (currentFNCD.getCommandQueue()) {					
						currentFNCD.getCommandQueue().notifyAll();	
					}
					
				}
			}
			break;
			case 4:
			{
				if (currentFNCD == null) {
					System.out.println("Please chooose FNCD (option 1)");
				}else {
					currentFNCD.getCommandQueue().add(new SecondSalespersonCommand());
					
					synchronized (currentFNCD.getCommandQueue()) {					
						currentFNCD.getCommandQueue().notifyAll();	
					}
					
				}
			}
			break;
			case 5: //show and choose the vehicle
			{
				if (currentFNCD == null) {
					System.out.println("Please chooose FNCD (option 1)");
				}else {
					currentFNCD.getCommandQueue().add(new CurrentStoreInventoryCommand());
					
					synchronized (currentFNCD.getCommandQueue()) {					
						currentFNCD.getCommandQueue().notifyAll();	
					}		
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					System.out.print("Please enter the vehicle name: ");
					String name = input.readLine();
					
					chosenVehicle = currentFNCD.getVehicle(name);
					if (chosenVehicle == null) {
						System.out.println("Vehicle not found!");
					}
				}
			}
			break;
			case 6: //Ask the salesperson for all details on a user selected inventory item
			{
				if (currentFNCD == null) {
					System.out.println("Please chooose FNCD (option 1)");
				}else if (chosenVehicle == null) {
					System.out.println("Please chooose Vehicle (option 5)");
				}else {
					currentFNCD.getCommandQueue().add(new VehicleDetailsCommand(chosenVehicle));
					
					synchronized (currentFNCD.getCommandQueue()) {					
						currentFNCD.getCommandQueue().notifyAll();	
					}					
				}
			}
			break;
			case 7: //Buy a normal inventory item from the salesperson (if the item is in inventory), 
			{
				if (currentFNCD == null) {
					System.out.println("Please chooose FNCD (option 1)");
				}else if (chosenVehicle == null) {
					System.out.println("Please chooose Vehicle (option 5)");
				}else {
					currentFNCD.getCommandQueue().add(new BuyNormalInventoryItem(chosenVehicle));
					
					synchronized (currentFNCD.getCommandQueue()) {					
						currentFNCD.getCommandQueue().notifyAll();	
					}					
				}
			}
			break;
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// display menu and read selection
			System.out.println();
			selection = menu();
		}
		
		//add quit commands
		for (FNCD fncd: FNCDs) {
			synchronized (fncd.getCommandQueue()) {					
				fncd.getCommandQueue().add(new QuitCommand());
				fncd.getCommandQueue().notifyAll();		
			}
		}
	}
	
	//get FNCD by name
	private FNCD getFNCD(String name) {
		for (FNCD fncd: FNCDs) {
			if (fncd.getName().equalsIgnoreCase(name)) {
				return fncd;
			}
		}
		return null;
	}
    
	/*
	 * read an integer from standard input
	 */
	private int readInt() throws IOException {

		int val = 0;

		String line = input.readLine();// a line

		try {
			val = Integer.parseInt(line);
		} catch (Exception e) {

		}

		return val;
	}
	
	/**
	 * display menu and return selection
	 *
	 * @return selection
	 * @throws IOException 
	 */
	private int menu() throws IOException {

		System.out.println();
		System.out.println("1. Select one of the FNCD locations to issue commands to");
		System.out.println("2. Ask the salesperson their name (should reply with employee's name)");
		System.out.println("3. Ask the salesperson what time it is (should return the system time)");
		System.out.println("4. Ask for a different salesperson (select another salesperson for the transactions)");
		System.out.println("5. Ask the salesperson for current store inventory (to allow selecting an item)");
		System.out.println("6. Ask the salesperson for all details on a user selected inventory item");
		System.out.println("7. Buy a normal inventory item from the salesperson ");
		System.out.println("0. Exit");
		System.out.print("? ");
		
		return readInt();
	}
}