import java.util.ArrayList;

public class FNCD {

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
        this.date = 1;
        this.internList = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            Interns newIntern = new Interns(Integer.toString(i));
            internList.add(newIntern);
        }
        for(int j = 0; j < 3; j ++){
            System.out.println(internList.get(j).getName());
        }
    }
    public void simulation(){
        while(date < simTime){

            if(date % 7 != 0 ){

            }
        }

    }
    public void startDay(){

    }

    public void hire(){

    }

    public void endDay(){

    }

    public void noMoney(){

    }

    public int numOfBuyer(){
        return 0;
    }

}

