import java.util.ArrayList;

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
            }
        }
        if(mechanicsList.size() != 3){
            int tempLength = mechanicsList.size();
            for(int i = 0; i < maxSize-tempLength; i++){
                mechanicsList.add(new Mechanics("M" + i ));
            }
        }
        if(salespeopleList.size() != 3){
            int tempLength = salespeopleList.size();
            for(int i = 0; i < maxSize-tempLength; i++){
                salespeopleList.add(new Salesperson("S" + i));
            }
        }

    }

    public void endDay(){

    }

    public void noMoney(){

    }

    public int numOfBuyer(){
        return 0;
    }

}

