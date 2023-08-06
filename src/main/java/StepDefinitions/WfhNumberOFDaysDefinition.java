package StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import resources.DriverClass;
import resources.LocatorsFile;

import java.time.Duration;
import java.util.List;

public class WfhNumberOFDaysDefinition {

    WebDriver driver = DriverClass.setUp();

    @Given("^Verify you're on dashboard page of MIS$")
    public String verifyYouReOnDashboardPageOfMIS() throws InterruptedException {
        Thread.sleep(10000);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        String code = driver.findElement(LocatorsFile.employeeCode).getText();
        System.out.println(code);
        if(code.isEmpty()){
            return "We are not on DashBoard Page!";
        }
        return "We are on DashBoard Page!";
    }

    @Then("^Get the number of days of Work from Home you applied in the month$")
    public void getTheNumberOfDaysOfWorkFromHome() throws InterruptedException {
        Thread.sleep(10000);
        List<WebElement> wfhDays = driver.findElements(LocatorsFile.wfhCountChart);
        int countWfhDays = 0;
        for(WebElement webElement : wfhDays){
            countWfhDays++;
            System.out.println(webElement.getAttribute("data-date"));
        }
        System.out.println("Total count of Work From Home days is : " + countWfhDays);
    }
}
