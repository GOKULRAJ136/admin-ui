package io.mosip.testrig.adminui.testcase;
import java.io.IOException;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import io.mosip.testrig.adminui.utility.BaseClass;
import io.mosip.testrig.adminui.utility.Commons;

public class DeviceSpecificationTest extends BaseClass{


	@Test
	public void deviceSpecCRUD() throws IOException {
		String devicespec="admin/masterdata/device-specs/view";	    
		Commons.click(driver(),By.xpath("//a[@href='#/admin/masterdata']"));
		Commons.click(driver(),By.id(devicespec));
		Commons.click(driver(),By.id("Create"));

		Commons.enter(driver(),By.id("name"),data);
		Commons.enter(driver(),By.id("description"),data);
		Commons.enter(driver(),By.id("brand"),data);
		Commons.enter(driver(),By.id("model"),data);
		Commons.enter(driver(),By.id("minDriverversion"),data);
		Commons.dropdown(driver(),By.id("deviceTypeCode"));

		Commons.create(driver());
		Commons.filter(driver(), By.id("name"), data);
		Commons.edit(driver(),data+1,By.id("name"));
		Commons.filter(driver(), By.id("name"), data+1);
		Commons.activate(driver());
		Commons.edit(driver(),data+2,By.id("name"));
		Commons.filter(driver(), By.id("name"), data+2);
		Commons.deactivate(driver());
	}
}
