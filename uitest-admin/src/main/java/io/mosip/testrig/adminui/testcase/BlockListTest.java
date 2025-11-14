package io.mosip.testrig.adminui.testcase;

import java.io.IOException;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import io.mosip.testrig.adminui.kernel.util.ConfigManager;
import io.mosip.testrig.adminui.utility.BaseClass;
import io.mosip.testrig.adminui.utility.Commons;


public class BlockListTest extends BaseClass {

	@Test
	public void blocklistedwordsCRUD() throws IOException {
		String blocklistedWord=ConfigManager.getdummyData();
		String idBlocklisted="admin/masterdata/blocklisted-words/view";
		Commons.click(driver(), By.xpath("//a[@href='#/admin/masterdata']"));
		Commons.click(driver(), By.id(idBlocklisted));
		Commons.click(driver(), By.id("Create")); 
		Commons.enter(driver(), By.id("word"), blocklistedWord); 
		Commons.enter(driver(), By.id("description"), data);
		Commons.create(driver());
		Commons.filter(driver(), By.id("word"), blocklistedWord);
		Commons.edit(driver(),blocklistedWord+"auto",By.id("word"));
		Commons.filter(driver(), By.id("word"), blocklistedWord+"A");
		Commons.activate(driver());
		Commons.edit(driver(),blocklistedWord+"B",By.id("word"));
		Commons.filter(driver(), By.id("word"), blocklistedWord+"B");
		Commons.deactivate(driver());


	}
}
