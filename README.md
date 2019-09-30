# DocumentExplorerAssignment
Assignment to create a framework to automate Document Explorer application

Framework used : Page Object Model, TestNG
Browsers : Chrome (Extensible)
Language Used : Java
Build Management Tool : Maven
Html Reporting Tool : Extent Reports
Test cases Automated : 9 (Extensible)

Reasons for Page Object:

Page object model is easy to maintain and its readability is more when compared to other frameworks.
Each page related elements and methods are stored in a class called PageObject.
By using PageObjects, it is easy to debug or easy to fix if there are any issues.

Note: Please be informed that there are still few areas where the framework can be improvised, 
For instance, test data can be stored in spreadsheet and can be called using Apache POI library

Running Project:

Project can be run using pom.xml which inturn runs testng.xml which runs all the test cases in the suite.
Application url and Chrome driver path is stored in config.properties file.
Html report for the project gets stored in Test Report folder in root project.
On failure, a screenshot gets attached to the html report generated and a copy also gets stored in Screenshots folder.

Note: There are few areas like updating failures to html report that can still be improved further.






