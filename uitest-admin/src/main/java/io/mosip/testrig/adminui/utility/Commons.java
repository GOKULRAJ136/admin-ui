package io.mosip.testrig.adminui.utility;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

import io.mosip.testrig.adminui.kernel.util.ConfigManager;

public class Commons  extends BaseClass{
	private static final Logger logger = Logger.getLogger(Commons.class);

	public static String appendDate=getPreAppend()+getDateTime();

	public static String getDateTime()	  {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);
	}

	public  static void filter(WebDriver driver, By by,String data) throws IOException {
		try {
			logger.info("Inside Filter " + by + data);
			Commons.click(driver, By.id("Filter")); 
			wait(3000);
			Commons.enter(driver, by, data); 
			wait(3000);
			Commons.click(driver, By.id("applyTxt"));
		}
		catch (Exception e) {
			Reporter.log("<p><img src='data:image/png;base64," + Screenshot.ClickScreenshot(driver) + "' width='900' height='450'/></p>");
			logger.info(e.getMessage());
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", driver.findElement(by));

		}
	}

	public  static void filterCenter(WebDriver driver, By by,String data) throws IOException {
		logger.info("Inside filterCenter " + by + data);
		try {
			Commons.click(driver, By.id("Filter")); 

			Commons.dropdowncenter(driver, by, data); 

			Commons.click(driver, By.id("applyTxt")); 
		}
		catch (Exception e) {
			Reporter.log("<p><img src='data:image/png;base64," + Screenshot.ClickScreenshot(driver) + "' width='900' height='450'/></p>");
			logger.info(e.getMessage());
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", driver.findElement(by));

		}
	}

	public  static void click(WebDriver driver, By by) throws IOException {
		logger.info("Clicking " + by );
		try {
			wait(1000);
			driver.findElement(by).click();
			wait(500);
		} catch (Exception e) {
			Reporter.log("<p><img src='data:image/png;base64," + Screenshot.ClickScreenshot(driver) + "' width='900' height='450'/></p>");
			logger.error( e.getMessage());
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", driver.findElement(by));
		}
	}

	public static void enter(WebDriver driver, By by,String value) throws IOException {
		logger.info("Entering " + by +value);
		try {
			driver.findElement(by).clear();
			driver.findElement(by).sendKeys(value);
		}catch (StaleElementReferenceException sere) {
			Reporter.log("<p><img src='data:image/png;base64," + Screenshot.ClickScreenshot(driver) + "' width='900' height='450'/></p>");
			driver.findElement(by).sendKeys(value);
		}
		catch (TimeoutException toe) {
			Reporter.log("<p><img src='data:image/png;base64," + Screenshot.ClickScreenshot(driver) + "' width='900' height='450'/></p>");

			driver.findElement(by).sendKeys(value);
			logger.info( "Element identified by " + by.toString() + " was not clickable after 20 seconds");
		} 
		catch (Exception e) {
			Reporter.log("<p><img src='data:image/png;base64," + Screenshot.ClickScreenshot(driver) + "' width='900' height='450'/></p>");
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", driver.findElement(by));
		}

	}

	public static void dropdown(WebDriver driver, By by) throws IOException{
		logger.info("Selecting DropDown Index Zero Value " + by );
		try {
			wait(500);
			click(driver,by);//REGION
			wait(500);

			String att= driver.findElement(by).getAttribute("aria-owns");
			String[] list=att.split(" ");
			click(driver,By.id(list[0]));
			wait(500);
		}catch (Exception e) {
			Reporter.log("<p><img src='data:image/png;base64," + Screenshot.ClickScreenshot(driver) + "' width='900' height='450'/></p>");
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", driver.findElement(by));

		}
	}

	public static void dropdown(WebDriver driver, By by,String value){
		logger.info("Selecting DropDown By Value " + by +value );

		try {
			wait(500);
			click(driver,by);
			wait(500);
			String val="'"+value +"'";
			click(driver,By.xpath("//span[contains(text(),"+val+")]"));
			wait(500);
		}catch (Exception e) {
			logger.info(e.getMessage());
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", driver.findElement(by));

		}
	}

	public static void dropdowncenter(WebDriver driver, By by,String value){
		logger.info("Selecting DropDown By Value " + by +value );

		try {
			wait(500);
			click(driver,by);
			wait(500);
			click(driver,By.id(value));
			wait(500);

		}catch (Exception e) {
			logger.info(e.getMessage());
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", driver.findElement(by));

		}
	}

	public static void dropdown(WebDriver driver, By by,By value){
		logger.info("Selecting DropDown By Value " + by +value );
		try {  
			wait(500);
			click(driver,by);
			wait(500);
			click(driver,value);
			wait(500);
		}catch (Exception e) {
			logger.info(e.getMessage());
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", driver.findElement(by));

		}
	}

	public static String getTestData(){
		return JsonUtil.readJsonFileText("TestData.json");
	}

	public static void deactivate(WebDriver driver) throws IOException {
		Commons.click(driver,By.id("ellipsis-button0"));
		Commons.click(driver, By.id("Deactivate0")); 
		if(isElementDisplayed(By.id("confirmpopup"))) 
			Commons.click(driver,By.id("confirmpopup")); 
		Commons.click(driver, By.id("confirmmessagepopup")); 
		logger.info("Click deactivate and Confirm");
	}

	public static void activate(WebDriver driver) throws IOException {
		Commons.click(driver,By.id("ellipsis-button0"));
		Commons.click(driver, By.id("Activate0")); 
		if(isElementDisplayed(By.id("confirmpopup"))) 
			Commons.click(driver,By.id("confirmpopup")); 
		Commons.click(driver, By.id("confirmmessagepopup")); 
		logger.info("Click activate and Confirm");
	}

	public static void edit(WebDriver driver,String data,By by) {

		try {
			Commons.click(driver,By.id("ellipsis-button0"));
			Commons.click(driver, By.id("Edit0")); 

			Assert.assertNotEquals(data,
					driver.findElement(by).getText());
			driver.findElement(by).clear();

			Commons.enter(driver, by, data);

			Commons.click(driver, By.id("createButton"));
			if(isElementDisplayed(By.id("confirmpopup"))) 
				Commons.click(driver,By.id("confirmpopup"));
			Commons.click(driver, By.id("confirmmessagepopup")); 

			logger.info("Click Edit and Confirm" + by + data);
		}
		catch (Exception e) {
			logger.info(e.getMessage());
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", driver.findElement(by));

		}
	}

	public static void editRes(WebDriver driver,String data,By by) throws IOException {
		try {
			Commons.click(driver,By.id("ellipsis-button0"));
			Commons.click(driver, By.id("Edit0")); 
			wait(3000);
			Assert.assertNotEquals(data,
					driver.findElement(by).getText());
			wait(3000);
			driver.findElement(by).clear();

			Commons.enter(driver, by, data);

			Commons.click(driver, By.id("createButton"));
			if(isElementDisplayed(By.id("confirmpopup"))) 
				Commons.click(driver,By.id("confirmpopup")); 
			Commons.click(driver, By.id("confirmmessagepopup")); 

			logger.info("Click Edit and Confirm" + by + data);
		}catch (Exception e) {
			Reporter.log("<p><img src='data:image/png;base64," + Screenshot.ClickScreenshot(driver) + "' width='900' height='450'/></p>");
			logger.info(e.getMessage());
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", driver.findElement(by));

		}
	}

	public static void editCenter(WebDriver driver,String data,By by) {
		try {
			Commons.click(driver,By.id("ellipsis-button0"));
			Commons.click(driver, By.id("Edit0")); 

			Assert.assertNotEquals(data,
					driver.findElement(by).getText());
			driver.findElement(by).clear();

			Commons.enter(driver, by, data);

			Commons.click(driver, By.xpath("(//*[@id='createButton'])[1]"));
			if(isElementDisplayed(By.id("confirmpopup"))) 
				Commons.click(driver,By.id("confirmpopup")); 
			Commons.click(driver, By.id("confirmmessagepopup")); 

			Commons.click(driver,  By.xpath("(//*[@id='cancel'])[1]"));
			Commons.click(driver,  By.xpath("(//*[@id='cancel'])[1]"));
			logger.info("Click editCenter and Confirm" + by + data);
		}
		catch (Exception e) {
			logger.info(e.getMessage());
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", driver.findElement(by));

		}
	}

	public static void create(WebDriver driver) throws IOException {
		Commons.click(driver, By.xpath("//button[@id='createButton']")); 
		if(isElementDisplayed(By.id("confirmpopup"))) 
			Commons.click(driver, By.id("confirmpopup")); 
		Commons.click(driver, By.id("confirmmessagepopup"));
		logger.info("Click create");
	}

	public static void createRes(WebDriver driver) throws IOException {
		Commons.click(driver, By.xpath("//button[@id='createButton']"));
		if(isElementDisplayed(By.id("confirmpopup"))) 
			Commons.click(driver,By.id("confirmpopup")); 
		Commons.click(driver, By.id("confirmmessagepopup")); 
		logger.info("Click and confirm");
	}

	public static void decommission(WebDriver driver) throws IOException {
		Commons.click(driver,By.id("ellipsis-button0"));
		Commons.click(driver,By.id("Decommission0"));
		if(isElementDisplayed(By.id("confirmpopup"))) 
			Commons.click(driver,By.id("confirmpopup")); 
		Commons.click(driver, By.id("confirmmessagepopup")); 
		logger.info("Click decommission and confirm");
	}

	public static String getPreAppend() {
		String preappend = null;
		try {
			preappend =ConfigManager.getpreappend();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return preappend;
	}

	public static void calendar(String date) throws IOException {
		String a=date.replaceAll("/","");
		String mon="";
		if(a.substring(0,2).equals("10")) {
			mon=	a.substring(0,2);
		}else {
			mon=a.substring(0,2).replace("0","");
		}
		String d="";
		if(a.substring(2,4).equals("10") || a.substring(2,4).equals("20")||a.substring(2,4).equals("30")) {
			d=a.substring(2,4);
		}else {
			d=a.substring(2,4).replace("0","");
		}

		int month=Integer.parseInt(mon)  ;
		int day=Integer.parseInt(d);
		int year=Integer.parseInt(a.substring(4,8));
		try {
			Commons.click(driver(),By.xpath("//*[@class='mat-datepicker-toggle']//button"));
			wait(500);
			Commons.click(driver(),By.xpath("//*[@class='mat-calendar-arrow']"));
			wait(500);
			Commons.click(driver(),By.xpath("//*[text()='"+year+"']"));
			wait(500);
			List<WebElement> cli=  driver().findElements(By.xpath("//*[@class='mat-calendar-body-cell-content']"));
			cli.get(month-1).click();
			wait(500);
			Commons.click(driver(),By.xpath("//*[text()='"+day+"']"));
		}catch (Exception e) {
			Reporter.log("<p><img src='data:image/png;base64," + Screenshot.ClickScreenshot(driver()) + "' width='900' height='450'/></p>");
			logger.info(e.getMessage());
		}
	}
	
	public static void calendar(String date, String locale) throws IOException {
	    String a = date.replaceAll("/", "");
	    int month = Integer.parseInt(a.substring(0, 2));
	    int day = Integer.parseInt(a.substring(2, 4));
	    int year = Integer.parseInt(a.substring(4, 8));

	    try {
	        // Open calendar
	        Commons.click(driver(), By.xpath("//*[@class='mat-datepicker-toggle']//button"));
	        Thread.sleep(500);

	        // Expand year/month selector
	        Commons.click(driver(), By.xpath("//*[@class='mat-calendar-arrow']"));
	        Thread.sleep(500);

	        // Click year
	        String yearText = convertDigits(String.valueOf(year), locale);
	        Commons.click(driver(), By.xpath("//*[normalize-space(text())='" + yearText + "']"));
	        Thread.sleep(500);

	        // Select month (index based to avoid translation issues)
	        List<WebElement> months = driver().findElements(By.xpath("//*[@class='mat-calendar-body-cell-content']"));
	        months.get(month - 1).click();
	        Thread.sleep(500);

	        // Select day (convert if Arabic locale)
	        String dayText = convertDigits(String.valueOf(day), locale);
	        Commons.click(driver(), By.xpath("//*[normalize-space(text())='" + dayText + "']"));
	    } catch (Exception e) {
	        Reporter.log("<p><img src='data:image/png;base64," + Screenshot.ClickScreenshot(driver()) + "' width='900' height='450'/></p>");
	        logger.info(e.getMessage());
	    }
	}

	public static void wait(int waitTime) {
		try {
			Thread.sleep(waitTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static boolean isElementDisplayed(By by) {
	    try {
	        wait(500); // Make sure you have a proper wait method or use Thread.sleep(500);
	        return driver().findElement(by).isDisplayed();
	    } catch (Exception e) {
	        return false;
	    }
	}

}
