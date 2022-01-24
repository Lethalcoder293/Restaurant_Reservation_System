# Restaurant_Reservation_System
A restaurant reservation and point of sale system implemented as a command line application in JAVA. This was the course project for AY2021 Sem 1 in CZ2002-Object oriented design and programming at Nanyang Technological University, Singapore. 
## Authors
Harsh Rao Dhanyamraju (https://github.com/HarshRaoD)  
Sanskar Deepak Malavade (https://github.com/Lethalcoder293)  
Kshitij Parashar (https://github.com/xitij27)
Benjamin Ong Chee Meng  
Chng Wei Ming Samuel  
## Video Demonstration
https://youtu.be/Z5SBnoOojsg  
## Features
1. Create reservation booking(after checking table availibility).  
2. Check/Remove reservation booking.  
3. Take order  
4. Print order invoice  
5. Reservation automatically cancels if customer is late by X minutes(X set by the restaurant) .  
6. Allows overbooking of tables if time difference between reservations is greater than Y minutes(Y set by the restaurant).  
7. Generates daily sales report.  
8. Gives membership discount.  
9. Stores menu, staff, and table details(in txt files).  
10. Menu, staff, and table details can be edited by the restaurant.  
11. Menu allows for combos of different items to be sold at a discounted price.  

(Please watch the above video for a detailed explaination of the same)  
## File contents
UMLClassDiagram.vpp - Contains UML class diagram  
UMLSequenceDiagram.vpp - Contains UML sequence diagram of manually and automatically cancelling a reservation  
#### CZ2002_Group_Project_SourceCode
MainDetails.txt - Stores the restaurant name, delay time , overbooking time  
Menu.txt - Stores the menu   
StaffList.txt - Stores the list of employees and all their details.  
TableList.txt - Stores the list of tables and their capacities  
bin - Contains all the JAVA bytecode files  
src - Contains all the JAVA source code files  
#### Java Docs
Contains documentation for all classes(files) and functions in src(Please download the files and view in browser)  
## Design Considerations  
1. Data Encapsulation - Use of private variables for data hiding, for example in
Menu_Item class, etc.
2. Data Abstraction - Example: the Menu class takes care of all activities related to the
menu. If an item has to be added to the menu, we can just call addItem(). This ensures
that the user does not need to know about the implementation of how an item is added
to the menu.
3. Method Overloading - findReservation() in the Restaurant class, this method returns
the index of a particular reservation from the reservations[]. This reservation is found
using either contact number (findReservation(long num)) or the name of the customer
(findReservation(String name))
4. Use of static variables and methods - We have used static variables and methods in
the classes Menu, TableList, StaffList, Restaurant, etc., as there is only one copy of
these variables throughout the entire program. For example, the items array in the
Menu class is static as there is only one menu in the entire restaurant and we do not
need to make a new Menu object for each and every time we access variables or
methods of the Menu class.
## Approach Taken
#### 1. Overbooking of Reservations on the same table:
This is the procedure we used to add a new reservation compliant with overbooking.  
I. Take the number of people and time from the user.  
II. For each table in the restaurant, check if the capacity of the table is greater than or
equal to the number of people in the reservation.  
III. If capacity is not enough, go to the next table.  
IV. If capacity is enough, check all reservations with the same table to see if there is
any reservation within a certain time (overbooking time) of the time we want to
book the reservation.  
V. If yes, go to the next table.  
VI. If no, then allot this table to the reservation and take the customers particulars.  
VII. If no tables are found inform the user.  
#### 2. How auto cancel reservation works:
Auto cancel reservation is the process we use to cancel the reservation after XX time
(the value of XX is stored in a variable delayTime) this has been explained with the
help of the UML sequence diagram later in the report.  
#### 3. How Sales Revenue report works:
In the Menu class, we have an integer array grossSales which stores the amount of
each ala carte item and combo sold. While printing the sales revenue report, we
multiply the amount sold with the price of each item to get the total sales for each
item, after which we sum them up to get the total sales for the entire restaurant.  
## Assumptions Made
Assumptions 1-5 were made by our team whereas assumptions 6-10 were given in the project description by the course instructor.  
  
1. There is no point in letting a reservation cancel itself after xx time if a new customer
does not come (i.e., another reservation has not been made).  
2. Two reservations can be made on the same table as long as the time difference
between the two is greater than the overbooking time (which is set by the restaurant
owner).  
3. The restaurant staff will get used to the menu indices so when we remove an item
from the menu, it is better to skip an index instead of re-arranging the whole menu.  
4. If the restaurant wants to cater to walk-in customers, they will make a reservation
with the time of booking as the current time (and so, there is no need to see if a table
is reserved or not since the system will handle the reservation based on table
availability including walk-ins).  
5. The Sales Revenue Report is a daily report that will be printed by the staff at the end
of the day.  
6. The currency will be in Singapore Dollar (SGD) and Good and Services Tax (GST)
and service charge must be included in the order invoice.  
7. Once an order invoice is printed, it is assumed that payment has been made and the
table is vacated.  
8. Customer with membership card will be entitled to discount.  
9. There is no requirement for access control and there is no need for authentication
(login/logout) in order to use the application.  
10. There is no need to interface with external system, eg- Payment, printer, etc.  

## UML Sequence Diagram Explanation
1. Manually cancelling a reservation 􀂱 cancelReservation() method calls the
findReservation() method which returns the index of the element in the reservations[]
array which matches either the name or contact number of the customer.  
cancelReservation() then deletes the corresponding index by making it equal to null.
2. Automatically cancelling a reservation after XX time - addReservation() method
of the Restaurant class calls cleanReservation() which loops through the reservations
array and cancels all reservations whose time of booking is more than XX time before
the current time. After which, the control returns back to addReservation(). (We have
omitted the remaining execution of addReservation() from the sequence diagram is it
is not relevant to automatic cancellation)  
3. Service Reservation 􀂱 serviceReservation() method calls the findReservation()
method which returns the index of the element in the reservations[] array which
matches either the name or contact number of the customer. serviceReservation() then
sets the hasArrived attribute of the reservation to true. This prevents the reservation
from automatically cancelling when the customer is seated at the table.  

## Extra Test Cases
https://www.youtube.com/watch?v=Yst73l6vwN8
