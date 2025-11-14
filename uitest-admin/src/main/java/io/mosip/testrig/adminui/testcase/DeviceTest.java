package io.mosip.testrig.adminui.testcase;


import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import io.mosip.testrig.adminui.kernel.util.ConfigManager;
import io.mosip.testrig.adminui.utility.BaseClass;
import io.mosip.testrig.adminui.utility.Commons;

public class DeviceTest extends BaseClass{
	@Test
	public void deviceCRUD() throws InterruptedException, IOException {
		String validityDate;

		validityDate = ConfigManager.getvalidityDate();
		Commons.click(driver(),By.id("admin/resources"));
		Commons.click(driver(),By.id("/admin/resources/devices"));
		Commons.click(driver(),By.id("Create Device"));
		Commons.enter(driver(), By.id("name"),data);
		Commons.enter(driver(), By.id("serialNumber"),data);
		Commons.enter(driver(), By.id("macAddress"),"1.1234");
		Commons.enter(driver(), By.id("ipAddress"),"2.2345");
		Commons.calendar(validityDate);
		Commons.dropdown(driver(),By.id("deviceSpecId"));
		Commons.dropdown(driver(),By.id("regCenterId"));
		Commons.createRes(driver());
		Commons.filter(driver(), By.id("name"), data);
		Commons.editRes(driver(),data+1,By.id("name"));
		Commons.filter(driver(), By.id("name"), data+1);
		Commons.activate(driver());
		Commons.editRes(driver(),data+2,By.id("name"));
		Commons.filter(driver(), By.id("name"), data+2);
		Commons.deactivate(driver());
		Commons.decommission(driver());

	}
}
