package StepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import resources.DriverClass;
import resources.LocatorsFile;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class QAEmployeeDirDefinition {

    WebDriver driver = DriverClass.setUp();
    JSONArray jsonArray = new JSONArray();

    @Given("Navigate to Employee Directory on MIS")
    public void navigateToEmployeeDirectoryOnMIS() throws InterruptedException {
        driver.findElement(LocatorsFile.employeeDir).click();
        Thread.sleep(15000);
    }

    @When("Set entries and type QA in Search Box")
    public void setEntriesAndTypeQAInSearchBox() throws InterruptedException {
//        JSONObject jsonObjectFinal = new JSONObject();
        List<WebElement> noOfEntries = driver.findElements(LocatorsFile.noEntriesFilter);
        noOfEntries.get(3).click();
        driver.findElement(LocatorsFile.employeeSearch).sendKeys("QA");
        Thread.sleep(2000);
    }

    @Then("Extract the Employee Name, Designation and Delivery Council")
    public void extractTheEmployeeNameDesignationAndDeliveryCouncil() throws InterruptedException {
        List<WebElement> lst = driver.findElements(LocatorsFile.paginateBtn);
        int noOfEntriesOnPage = Integer.parseInt(lst.get(lst.size() - 2).getText());
        for(int i = 1; i <= noOfEntriesOnPage; i++){
            int employeeCount = driver.findElements(LocatorsFile.countEmployeeOnPage).size();
            for(int j = 1; j <= employeeCount; j++){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("Employee Name", driver.findElement(LocatorsFile.employeeName(j)).getText());
                jsonObject.put("Designation", driver.findElement(LocatorsFile.employeeDesignation(j)).getText());
                jsonObject.put("Delivery Council", driver.findElement(LocatorsFile.deliveryCouncil(j)).getText());
                jsonArray.put(jsonObject);
            }
            driver.findElement(LocatorsFile.nextBtn).click();
            Thread.sleep(2000);
        }
    }

    @And("Store the data into JOSN")
    public void storeTheDataIntoJOSN() throws IOException {
        FileOutputStream fos = new FileOutputStream("src/main/resources/data.json");
        fos.write(jsonArray.toString().getBytes());
        fos.close();
    }
}
