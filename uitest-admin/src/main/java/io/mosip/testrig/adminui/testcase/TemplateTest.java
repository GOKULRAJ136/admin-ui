package io.mosip.testrig.adminui.testcase;
import java.io.IOException;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import io.mosip.testrig.adminui.utility.BaseClass;
import io.mosip.testrig.adminui.utility.Commons;

public class TemplateTest extends BaseClass{


	@Test
	public void templateCRUD() throws IOException {
		String templatesid="admin/masterdata/templates/view";
		Commons.click(driver(),By.xpath("//a[@href='#/admin/masterdata']"));   
		Commons.click(driver(),By.id(templatesid));
		Commons.click(driver(),By.id("Create"));
		Commons.enter(driver(),By.id("name"),data);
		Commons.enter(driver(),By.id("description"),data);
		Commons.enter(driver(),By.id("model"),data);
		Commons.enter(driver(),By.id("fileText"),data);
		Commons.dropdown(driver(),By.id("fileFormatCode")); 
		Commons.dropdown(driver(),By.id("templateTypeCode"));    
		Commons.dropdown(driver(),By.id("moduleId"));    
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
