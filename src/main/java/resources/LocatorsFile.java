package resources;


import org.openqa.selenium.By;

public class LocatorsFile {
     public static By loginViaSSO = By.xpath("//button[text()='Login via SSO']");
     public static By microInput(String param){
          return By.xpath("//input[@type='"+param+"']");
     }
     public static By leaveManageButton = By.xpath("//span[text()='Leave Management']");
     public static By applyButton = By.xpath("(//span[@class='lbl' and text() = 'Apply'])[2]");
     public static By wfhHeader = By.xpath("//span[text() = 'Work From Home']");
     public static By selectClick = By.xpath("//select[@id='WorkFromHomeDate']");
     public static By selectDate = By.xpath("//select[@id='WorkFromHomeDate']//option");
     public static By wfhReason = By.xpath("//textarea[@id= 'WFHReason']");
     public static By submitBtn = By.xpath("//input[starts-with(@id, 'btnSubmit')]");
     public static By employeeCode = By.xpath("//div[@id='employeeCode']");
     public static By wfhCountChart = By.xpath("//tbody//tr//td[starts-with(@class, 'fc-day fc-widget-content') and contains(@class , 'leave-date') and @title='WFH']");
     public static By employeeDir = By.xpath("//span[text() = 'Employee Directory']");
     public static By noEntriesFilter = By.xpath("//select[starts-with(@name, 'tblEmployee')]//option");
     public static By employeeSearch = By.xpath("//input[@type='search']");
     public static By countEmployeeOnPage = By.xpath("//tbody//tr//td[1]//a");
     public static By employeeName(int employeePos){
          return By.xpath("(//tbody//tr//td[1]//a)[" + employeePos + ']');
     }
     public static By employeeDesignation(int employeePos){
          return By.xpath("(//tbody//tr//td[3])[" + employeePos + ']');
     }
     public static By deliveryCouncil(int employeePos){
          return By.xpath("(//tbody//tr//td[4])[" + employeePos + ']');
     }
     public static By nextBtn = By.xpath("//a[text() = 'Next']");
     public static By paginateBtn = By.xpath("//ul[@class='pagination']//li");
}
