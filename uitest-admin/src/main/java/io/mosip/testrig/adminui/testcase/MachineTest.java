package io.mosip.testrig.adminui.testcase;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import io.mosip.testrig.adminui.kernel.util.ConfigManager;
import io.mosip.testrig.adminui.utility.BaseClass;
import io.mosip.testrig.adminui.utility.Commons;

public class MachineTest extends BaseClass{

	@Test
	public void machineCRUD() throws Exception {
		String holidayDateCenter=ConfigManager.getholidayDateCenter();
		String publicKey=ConfigManager.getpublicKey();
		String signPublicKey=ConfigManager.getsignPublicKey();  
		Commons.click(driver(),By.id("admin/resources"));
		Commons.click(driver(),By.xpath("//a[@href='#/admin/resources/machines']"));
		Commons.click(driver(),By.id("Create Machine"));
		Commons.enter(driver(),By.id("name"),data);
		Commons.enter(driver(),By.id("serialNumber"),"1234567");
		Commons.enter(driver(),By.id("macAddress"),"1.2.3.4.5.6");
		Commons.enter(driver(),By.id("ipAddress"),"2.3.4.5.6");
		
		String loginLang = ConfigManager.getloginlang();
		Commons.calendar(holidayDateCenter, loginLang.toLowerCase());

		Commons.dropdown(driver(),By.id("machineSpecId"));
		Commons.enter(driver(),By.id("publicKey"),publicKey);	
		Commons.enter(driver(),By.id("signPublicKey"),signPublicKey);
		try{  
			Commons.dropdown(driver(), By.id("zone"));

		}catch(Exception e) {
		}
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
