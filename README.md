## HW1
Note: I was adding on more functionality beyond the requirements and ended up having to put in some temporary solutions while I figure out how to best continue working in those options. Everything works, but some of the variable passing isn't ideal (and I'm working on it). 

### Assumptions  

1. Scanner 
This HW assignment assumes that every function should be written so it can be run anywhere on its own. Because every function requires user input, one Scanner is stored in a class variable and used for every function. 

2. **stringToInt**  
Assumes that `Character.getNumericValue` is okay to use for converting the string to an int value. 

3. **Output**  
Assumes that all functions should be tested via an external file with "Test" in the filename. 

4. **UML**  
All UML and external documents are located in the HW1 folder. 

5. **Date**  
The Date class in exercise #6 can use the pre-existing Date object to get the current date, but it must implement everything else. Since no UML was requested for exercise #6, it wasn't included here. 
Date has three constructors: a default constructor, a constructor that takes ints as arguments, and a constructor that takes a String for the month. The constructor that takes ints as args is one-indexed rather than zero-indexed, to make the program accessible even to those unfamiliar with computer science principles. 
Any year input must be made up of at least four integers.  

6. Getters and Setters
Comments aren't written for getters and setters unless the function declaration is unclear. 

## HW2

1. Employee Payment  
Assumes that the Employee that the method is called on is the boss, not the employee to be paid. 
