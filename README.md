# WFH Appliance-Automation on MIS
This is Ticker Tape website automation using Selenium. It uses Selenium in Java, Cucumber to create a feature file and apache.POI dependency to dump data in excel.

# Project SetUp
1. Clone this repository.
2. Install the following dependencies:
   * Install Apache Maven: [Maven Installation Guide](https://maven.apache.org/install.html) since this is a Maven Project.
   * You will need Java SDK on your local.

# Run the Project
1. Post performing the above mentioned steps open the project in any editor of your choice(I have used IntelliJ for building this project).
2. Go to Feature file and write your email address and password in place of `email@geminisolutions.com` and `password`.
3. Navigate to the Runner file under `src/main/java/RunnerFiles/Runner`.
4. Hit the Run Button.

# Project Structure
1. `src/main/java/RunnerFiles` It contains the Runner file which glue the Stepdefinition and the feature file together.
2. `src/main/java/StepDefinitions/StepDefinition` It contains the definition of each of the feature defined in Feature file.
3. `src/main/java/StepDefinitions/driverClass` It contains the initialization of Webdriver.
4. `Features` It contains the Gherkin feature file which defines the scenarios covered here.
5. `pom.xml` It contains all the dependencies required for this project.

# Dependencies Used
* Cucumber
* Selenium
  
# Results
### Apply for WFH
![image](https://github.com/GemAyush/WFH-Automation-Assignment/assets/125482096/27819983-ed38-4e79-8d6f-386e468ea354)

### Get Number of Days WFH applied in the current Month
![image](https://github.com/GemAyush/WFH-Automation-Assignment/assets/125482096/f62ccb75-0598-4a8e-bcf7-e0f19f8ce2a2)

### QA Team Employee
![image](https://github.com/GemAyush/WFH-Automation-Assignment/assets/125482096/47856aa1-d264-41dc-bdb3-454cb359ef76)

