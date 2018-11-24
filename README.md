# selenium-project-test
This project is written in java using Selenium and Maven, and there is one basic test to check specific site.
Everything is set up and more tests can be added straight away.

## How To Execute
Before building, ensure that you have the most recent chrome\firefox\IE version.
To execute the tests just browse to the path where selenium-project-test is located.
Open a CMD terminal in this location, and type `mvn clean install` or execute the tests in your IDE. maven profiles for all browsers exists in the [pom.xml].

## Inputs
User can change the inputs of the test in the test_data.prperties file.

## Implemented Browsers
* Chrome
* Firefo
* Internet Explorer

## Page Objects Pattern
page object pattern is used to have reusable WebElements/small helper methods seperated from actual test classes and give the opportunity to have nice structured and easily readable tests.

## Generate HTML Report
To generate surefire HTML report, please run after the test the next command: 'mvn surefire-report:report'

## How to Run with Multipile Threads
Only to add in the list of environemnt parameters more browsers to test.
