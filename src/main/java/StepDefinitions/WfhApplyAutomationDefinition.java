package StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import resources.DriverClass;
import resources.LocatorsFile;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


public class WfhApplyAutomationDefinition {

    WebDriver driver = DriverClass.setUp();
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
    public void typeInAndMicrosoftSignInWindow(String email, String password) throws InterruptedException {
        Set<String> windowHandles = driver.getWindowHandles();
        String parentWindow = driver.getWindowHandle();
        System.out.println(windowHandles.size());
        Iterator<String> it = windowHandles.iterator();
        Thread.sleep(3000);
        while(it.hasNext()){
            String childWindow = it.next();
            if(!parentWindow.equals(childWindow)){
                driver.switchTo().window(childWindow);
                driver.findElement(LocatorsFile.microInputEmail).sendKeys(email);
                driver.findElement(LocatorsFile.microSubmitBtn).click();
                Thread.sleep(2000);
                driver.findElement(LocatorsFile.microPassword).sendKeys(password);
                driver.findElement(LocatorsFile.microSubmitBtn).click();
                Thread.sleep(2000);
                driver.findElement(LocatorsFile.microSubmitBtn).click();
            }

        }

//        System.out.println(driver.getTitle());
        driver.switchTo().window(parentWindow);
        Thread.sleep(50000);
//        System.out.println(driver.getTitle());
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
//        Thread.sleep(4000);
        driver.findElement(LocatorsFile.selectClick).click();
        List<WebElement> selectDate = driver.findElements(LocatorsFile.selectDate);
        selectDate.get(1).click();
        driver.findElement(LocatorsFile.wfhReason).sendKeys(reason);
        driver.findElement(LocatorsFile.submitBtn).click();
    }
}
