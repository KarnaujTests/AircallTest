# AircallTest

### Prerequisites 📋
In order to run the project locally you will need Java 11 JDK, Maven 3.6.3 and their corresponding environment 
variables configured on your system.  

Before executing the program, a file needs to be created (**cucumber.properties**) on project level with the 
following content:  
>_chromedriver = 
_phoneUrl = https://phone.aircall.io/login
_timeout=30
_remoteDriver=False
_#Properties login
_username1= 
_password1= 
_telephone1= 
_username2= 
_password2= 
_telephone2= 

###Running the project
To run the project, a Runner class needs to be run in order for the tests to be executed which are located in
*src/test/java/com/aircall/es/testscucumber/Runners*  

###Output results
A folder on the project root should be created with the name Report which will contain logs, screenshots and an 
**html report** in cucumber format

###Project Structure
####Page Factory definition (src/main/java/com/aircall/es/testscucumber)
Contains the definition of pages (with a top for defining the elements of the page and a lower
one for method/action definition) and a BasicPage definition with common actions
#####Generic functionalities (src/main/java/com/aircall/es/testscucumber/Utils)
Contains generic functionalities common to the different pages which differ from the ones in BasicPage on the nature,
been this ones more related to operations done in code or the framework setup
#####Step definitions (src/test/java/com/aircall/es/testscucumber/)
Definition of cucumber test definitions for future reusability and enable of feature steps to be defined and structured
#####Test Runners (src/test/java/com/aircall/es/testscucumber/Runners)
Runners to execute the feature test cases with their parameterization. RunDebug should be used to debug
the new test cases developed
#####Feature files defined (src/test/resources)
Definition of test scenarios which define the actions perform during test execution


