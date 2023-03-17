### CSCI 4/5448 OOAD
###### Group: Anuragini Sinha & Lin Shi
This is the repository for CSCI 4/5448 OOAD. <br>
This is the directory for project 4

#### Java Version: 19


#### What has changed between 4.1 UML and 4.2 UML


#### UML: 
##### The whole UML diagram:

##### UML for Staff:

##### UML for Vehicle:

##### UML for Observer:

##### UML for Strategy:

##### UML for decorator:



#### Extra credit:


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