package io.mosip.testrig.adminui.testcase;
import java.io.IOException;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import io.mosip.testrig.adminui.utility.BaseClass;
import io.mosip.testrig.adminui.utility.Commons;

public class DynamicFieldTest extends BaseClass{


	@Test
	public void dynamicFieldCRUD() throws IOException {
		Commons.click(driver(),By.xpath("//a[@href='#/admin/masterdata']"));
		Commons.click(driver(),By.id("createDynamicField"));
		Commons.enter(driver(),By.id("code"),data);
		Commons.enter(driver(),By.id("name"),"Automation");
		Commons.enter(driver(),By.id("description"),"Automation");
		Commons.enter(driver(),By.id("value"),data);
		Commons.create(driver());
		Commons.filter(driver(), By.id("description"), "Automation");
		Commons.edit(driver(),data+1,By.id("code"));
		Commons.filter(driver(), By.id("description"), "Automation");
		Commons.activate(driver());
		Commons.edit(driver(),data+2,By.id("code"));
		Commons.filter(driver(), By.id("description"), "Automation");
		Commons.deactivate(driver());

	}
}
