package io.mosip.testrig.adminui.testcase;
import java.io.IOException;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import io.mosip.testrig.adminui.utility.BaseClass;
import io.mosip.testrig.adminui.utility.Commons;

public class MachineSpecificationTest extends BaseClass{

	@Test
	public void machineSpecCRUD() throws IOException {
		String machinespec="admin/masterdata/machine-specs/view"; 
		Commons.click(driver(),By.xpath("//a[@href='#/admin/masterdata']"));
		Commons.click(driver(),By.id(machinespec));
		Commons.click(driver(),By.id("Create"));   
		Commons.enter(driver(),By.id("name"),data);
		Commons.enter(driver(),By.id("brand"),data);
		Commons.enter(driver(),By.id("model"),data);
		Commons.enter(driver(),By.id("minDriverversion"),data);
		Commons.enter(driver(),By.id("description"),data);
		Commons.dropdown(driver(),By.id("machineTypeCode"));        
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
