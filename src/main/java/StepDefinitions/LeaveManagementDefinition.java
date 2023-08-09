package StepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import resources.DriverClass;
import resources.LocatorsFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


public class LeaveManagementDefinition {

    WebDriver driver = DriverClass.setUp();
    JSONArray jsonArray = new JSONArray();
    @Given("^Verify Chrome Browser is Open$")
    public String verifyChrome() {
        if (driver.getWindowHandles().size() > 0) {
            return "Chrome browser is open.";
        }
        return "Chrome browser is not open.";
    }

    @Given("^You browse for mis portal \"([^\"]*)\"$")
    public void navigateToTheWebsite(String url) throws InterruptedException {
        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(10000);
    }

    @When("^You login there via SSO$")
    public void youLoginThereViaSSO() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(LocatorsFile.loginViaSSO));
        driver.findElement(LocatorsFile.loginViaSSO).click();
        Thread.sleep(5000);
    }

    @Then("^type-in \"([^\"]*)\" and \"([^\"]*)\" microsoft sign-in window$")
    public void typeInMicrosoftSignInWindow(String email, String password) throws InterruptedException {
        Set<String> windowHandles = driver.getWindowHandles();
        String parentWindow = driver.getWindowHandle();
        System.out.println(windowHandles.size());
        Iterator<String> it = windowHandles.iterator();
        Thread.sleep(3000);
        while(it.hasNext()){
            String childWindow = it.next();
            if(!parentWindow.equals(childWindow)){
                driver.switchTo().window(childWindow);
                driver.findElement(LocatorsFile.microInput("email")).sendKeys(email);
                driver.findElement(LocatorsFile.microInput("submit")).click();
                Thread.sleep(2000);
                driver.findElement(LocatorsFile.microInput("password")).sendKeys(password);
                driver.findElement(LocatorsFile.microInput("submit")).click();
                Thread.sleep(2000);
                driver.findElement(LocatorsFile.microInput("submit")).click();
            }

        }

        driver.switchTo().window(parentWindow);
        Thread.sleep(50000);
    }

    @When("^You click over the Leave Management button followed by Apply button$")
    public void performActionsToApply() throws InterruptedException {
        driver.findElement(LocatorsFile.leaveManageButton).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(LocatorsFile.applyButton).click();
    }

    @Then("^Apply for Work from Home on window with \"([^\"]*)\"$")
    public void applyForWorkFromHomeOnWindowWith(String reason) throws InterruptedException {
        Thread.sleep(3000);
        driver.findElement(LocatorsFile.wfhHeader).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(LocatorsFile.selectClick).click();
        List<WebElement> selectDate = driver.findElements(LocatorsFile.selectDate);
        selectDate.get(1).click();
        driver.findElement(LocatorsFile.wfhReason).sendKeys(reason);
        driver.findElement(LocatorsFile.submitBtn).click();
    }
    @Given("^Verify you're on dashboard page of MIS$")
    public String verifyMISDashboardPage() throws InterruptedException {
        driver.get("https://mis.geminisolutions.com/Dashboard/Index");
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

    @Given("Navigate to Employee Directory on MIS")
    public void navigateToEmployeeDirectoryOnMIS() throws InterruptedException {
        driver.findElement(LocatorsFile.employeeDir).click();
        Thread.sleep(15000);
    }

    @When("Set entries and type QA in Search Box")
    public void setEntriesAndTypeQAInSearchBox() throws InterruptedException {
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

    @And("Store the data into JSON")
    public void storeTheDataIntoJSON() throws IOException {
        FileOutputStream fos = new FileOutputStream("src/main/resources/data.json");
        fos.write(jsonArray.toString().getBytes());
        fos.close();
        driver.quit();
    }
}
