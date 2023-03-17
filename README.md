### CSCI 4/5448 OOAD
###### Group: Anuragini Sinha & Lin Shi
This is the repository for CSCI 4/5448 OOAD. <br>
This is the directory for project 4

#### Java Version: 19


#### What has changed between 4.1 UML and 4.2 UML
We had minimum changes in the Staff and Vehicle between 4.1 UML and 4.2 UML. When implementing the design, it was clear that all the factory pattern methods needs to return either Staff or Vehicle. Additionally, StaffFactory has another methods that accounts for promotion from intern to mechanics/salesperson. 
Furthermore, there has been some changes in the naming portion, such as from buildCars() to buildVehicle(). We created Tractor and Crane instead of Suv and Convertibles. In Logger and Tracker, only some naming has been modified, the overall structure of the singleton pattern has stayed the same.  

#### UML: 
The UML diagram for 4.2 could be found in [UML.md](uml.md). 

#### Junit Tests:
The Junit Test results could be found in [JunitResults.md](JUnitResults.md). 

#### Extra credit:
The extra credit (line graphs) could be found in [ExtraCredit.md](ExtraCredit.md). 

##### Assumption:
* The intern must wash two different vehicles for each iteration. If there is just one vehicle to wash, then the intern will only wash one car. (same applies to mechanics).
* We decided to use integer values based on the sample output.
* The simulation start on day 1 (Monday). Therefore, day 7, 14,... will be considered Sunday.
* All name are generated using **<location><position>_<unique id>**. Therefore, when an intern steps up to take a Mechanic or Salesperson's job, their position will change, but <unique id> and location will stay the same.
* Since we are just adding $250000 everytime that we do not have money, I am allowing the possibility of negative budget given that we will receive extra money at the end of the day. Additionally, given that we are now purchasing 6 instances of 9 types of vehicles, we needed to brorrow money a lot in the beginning. However, we decided to keep that instead of adding more to the initial budget, so there will be many instances of no money at the beginning. 
* We interpreted Sunday as race day only. We did see the piazza later, but ran out of time to fix it and given that the interpretation was okay, we kept it that way. 
* We include a numeric ending to all the vehicles in the order that it was purchased. Therefore, we thought it was okay to use that for the numeric ending for monster truck. 
* Based on the requirement, it seems that the use of Flow API was not required, so I used the Flow API, but we do not think that it was utilized correctly. 
* The tracker is used to keep track of both FNCD income at the end of the day. 
* We used the multi-threading to run the simulation in parallel. Therefore, instead of North complete all washing then South starting. Both of the FNCD are completing and announcing the task. Therefore, we added the name of the FNCD to try and distinguish that. 
* We used the cumulative vehicle sold, money earned by staff, and money earned by FNCD. Additionally, since the cumulative vehicle sold is very hard to see, we created its own graph to demonstrate the changes. 