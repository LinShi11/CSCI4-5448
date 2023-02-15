import java.util.ArrayList;
import java.util.Random;

public class FNCD {
    final int maxSize = 3;
    int budget;
    int dailySales;
    int date;
    int simTime;
    ArrayList<Vehicle> inventory;
    ArrayList<Staff> employee;

    ArrayList<Interns> internList;
    ArrayList<Mechanics> mechanicsList;
    ArrayList<Salesperson> salespeopleList;

    public FNCD(){
        this.budget = 500000;
        this.simTime = 30;
        this.date = 1;
        this.internList = new ArrayList<>();
        this.mechanicsList = new ArrayList<>();
        this.salespeopleList = new ArrayList<>();
        this.employee = new ArrayList<>();
        for(int i = 0; i < maxSize; i++){

            internList.add(new Interns("I" + i));
            mechanicsList.add(new Mechanics("m"+ i));
            salespeopleList.add(new Salesperson("s" + i));
        }
    }
    public void simulation(){
        while(date <= simTime){
            if(date % 7 != 0) {
                System.out.println("FNCD Day " + this.date);
                startDay();
                System.out.println(date);
                endDay();
                printStaff();
            } else{
                System.out.println("We are closed on Sunday");
            }
            date++;
        }

    }
    public void startDay(){
        System.out.println("Opening... (Current budget $" + this.budget + ")");
        System.out.println("We have " + numOfBuyer() + " Buyers");
        hire();
        noMoney();
    }

    public void hire(){
        if(internList.size() != 3){
            int tempLength = internList.size();
            for(int i = 0; i < maxSize-tempLength; i++){
                internList.add(new Interns("I" + i));
                System.out.println("hired new intern I" + i);
            }
        }
    }

    public void endDay(){
        quit();
        noMoney();
    }

    public void quit(){
        Random random = new Random();
        if(random.nextInt(10) == 0){
            int temp = random.nextInt(3);
            employee.add(internList.get(temp));
            internList.remove(temp);

            System.out.println("Intern " + employee.get(employee.size()-1).getName() + " has quit");
        }
        if(random.nextInt(10) == 0){
            int temp = random.nextInt(3);
            employee.add(mechanicsList.get(temp));
            mechanicsList.remove(temp);
            System.out.println("Mechanics "+ employee.get(employee.size()-1).getName() + " has quit");

            Interns stepup = internList.get(0);
            Mechanics newMechanics = new Mechanics(stepup.getName(), stepup.getTotalDaysWorked());
            mechanicsList.add(newMechanics);
            internList.remove(0);

            System.out.println("Intern " + stepup.getName() + " has stepped up and took the mechanics job");


        }
        if(random.nextInt(10) == 0){
            int temp = random.nextInt(3);
            employee.add(salespeopleList.get(temp));
            salespeopleList.remove(temp);
            System.out.println("Salesperson " + employee.get(employee.size()-1).getName() + " has quit");

            Interns steppup = internList.get(0);
            Salesperson newSalesperson = new Salesperson(steppup.getName(), steppup.getTotalDaysWorked());
            salespeopleList.add(newSalesperson);
            internList.remove(0);

            System.out.println("Intern " + steppup.getName() + " has stepped up and took the salesperson job");
        }
    }

    private void printStaff(){
        for (Interns interns : internList) {
            System.out.print(interns.getName());
        }
        System.out.println();
        for (Salesperson salesperson : salespeopleList) {
            System.out.print(salesperson.getName());
        }
        System.out.println();
        for (Mechanics mechanics : mechanicsList) {
            System.out.print(mechanics.getName());
        }
        System.out.println();
    }

    public void noMoney(){
        if(this.budget <= 0){
            this.budget += 250000;
            System.out.println("You ran out of money, so you borrowed $250,000 from the bank");
        }

    }

    public int numOfBuyer(){
        int num = 0;
        Random random = new Random();
        if(this.date % 7 == 5 || this.date % 7 == 6){
            System.out.println("it is the weekend (Friday/Saturday)");
            num = random.nextInt(9-2) + 2;
        }
        else{
            System.out.println("It is the weekdays");
            num = random.nextInt(6);
        }
        return num;

    }

}

