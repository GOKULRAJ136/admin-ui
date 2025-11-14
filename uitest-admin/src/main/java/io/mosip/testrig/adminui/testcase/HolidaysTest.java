package io.mosip.testrig.adminui.testcase;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import io.mosip.testrig.adminui.kernel.util.ConfigManager;
import io.mosip.testrig.adminui.utility.BaseClass;
import io.mosip.testrig.adminui.utility.Commons;

public class HolidaysTest extends BaseClass{

	@Test
	public void holidaysCRUD() throws Exception {
		String listofholidays="admin/masterdata/holiday/view";
		String holidayDate=ConfigManager.getholidayDate();
		Commons.click(driver(),By.xpath("//a[@href='#/admin/masterdata']"));
		Commons.click(driver(),By.id(listofholidays));
		Commons.click(driver(),By.id("Create"));
		Commons.enter(driver(),By.id("holidayName"),data);
		Commons.enter(driver(),By.id("holidayDesc"),data);
		Commons.calendar(holidayDate);
		Commons.dropdown(driver(),By.id("locationCode"));
		Commons.create(driver());
		Commons.filter(driver(), By.id("holidayName"), data);
		Commons.edit(driver(),data+1,By.id("holidayName"));
		Commons.filter(driver(), By.id("holidayName"), data+1);
		Commons.activate(driver());
		Commons.edit(driver(),data+2,By.id("holidayName"));
		Commons.filter(driver(), By.id("holidayName"), data+2);
		Commons.deactivate(driver());

	}
}
