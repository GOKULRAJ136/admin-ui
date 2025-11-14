package io.mosip.testrig.adminui.testcase;

import org.openqa.selenium.By;
import org.testng.Reporter;
import org.testng.annotations.Test;
import io.mosip.testrig.adminui.kernel.util.ConfigManager;
import io.mosip.testrig.adminui.utility.BaseClass;
import io.mosip.testrig.adminui.utility.BaseTestCaseFunc;
import io.mosip.testrig.adminui.utility.Commons;
public class CenterTest extends BaseClass{

	@Test

	public void centerCRUD() throws Exception {
		int totalHierarchyLevels=BaseTestCaseFunc.getHierarchyNumbers();
		Reporter.log("centerCRUD",true);
		String holidayDate=ConfigManager.getholidayDateCenter();
		Commons.click(driver(),By.id("admin/resources"));
		Commons.click(driver(),By.id("/admin/resources/centers")); 
		Commons.click(driver(), By.id("Create Center"));
		Commons.enter(driver(), By.id("name"), data);
		Commons.dropdown(driver(),By.id("centerTypeCode"));
		Commons.enter(driver(), By.id("contactPerson"),data);
		Commons.enter(driver(),By.id("contactPhone"),data);
		Commons.enter(driver(),By.id("longitude"),"1.1234");
		Commons.enter(driver(),By.id("latitude"),"2.2345");
		Commons.enter(driver(),By.id("addressLine1"),data);
		Commons.enter(driver(),By.id("addressLine2"),data);
		Commons.enter(driver(),By.id("addressLine3"),data);
		for(int i=1;i<=totalHierarchyLevels;i++) {
		Commons.dropdown(driver(), By.xpath("(//*[@id='fieldName'])["+i+"]"));
		}
		try{   
			Commons.dropdown(driver(), By.id("zone"));

		}catch(Exception e) {
		}
		Commons.dropdown(driver(), By.id("holidayZone")); 
		Commons.enter(driver(),By.id("noKiosk"),"10"); 
		Commons.dropdown(driver(),By.id("processingTime"),"45");
		Commons.dropdown(driver(),By.id("startTime"),"9:00 AM");
		Commons.dropdown(driver(),By.id("endTime"),"5:00 PM");
		Commons.dropdown(driver(),By.id("lunchStartTime"),"1:00 PM");
		Commons.dropdown(driver(),By.id("lunchEndTime"),"2:00 PM");
		Commons.click(driver(),By.cssSelector(".mat-list-item:nth-child(1) .mat-pseudo-checkbox"));
		Commons.click(driver(),By.cssSelector(".mat-list-item:nth-child(2) .mat-pseudo-checkbox"));
		Commons.click(driver(),By.cssSelector(".mat-list-item:nth-child(3) > .mat-list-item-content"));
		Commons.click(driver(),By.cssSelector(".mat-list-item:nth-child(4) > .mat-list-item-content"));
		Commons.click(driver(),By.cssSelector(".mat-list-item:nth-child(5) > .mat-list-item-content"));
		Commons.calendar(holidayDate);
		Commons.click(driver(), By.id("createExceptionalHoliday"));
		Commons.createRes(driver());
		Commons.filterCenter(driver(), By.id("name"), data);
		Commons.editCenter(driver(),data+1,By.id("name"));
		Commons.filterCenter(driver(), By.id("name"), data+1);	
		Commons.activate(driver());
		Commons.editCenter(driver(),data+2,By.id("name"));
		Commons.filterCenter(driver(), By.id("name"), data+2);
		Commons.deactivate(driver());
		Commons.decommission(driver());
	}
}
