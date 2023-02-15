import java.util.ArrayList;
import java.util.Random;

public class FNCD {
    final int maxSize = 3;
    double budget;
    double dailySales;
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
        this.date = 0;
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
        while(date < simTime){
            startDay();
            System.out.println(date);
            endDay();
            printStaff();
            date ++;
        }

    }
    public void startDay(){
        hire();
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
        for(int i = 0; i < internList.size(); i++){
            System.out.print(internList.get(i).getName());
        }
        System.out.println();
        for(int i = 0; i < salespeopleList.size(); i++){
            System.out.print(salespeopleList.get(i).getName());
        }
        System.out.println();
        for(int i =0; i < mechanicsList.size(); i++){
            System.out.print(mechanicsList.get(i).getName());
        }
        System.out.println();
    }

    public void noMoney(){

    }

    public int numOfBuyer(){
        return 0;
    }

}

