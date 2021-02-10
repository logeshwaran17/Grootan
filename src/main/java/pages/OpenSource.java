package pages;

import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import base.SiteSpecificMethods;

public class OpenSource extends SiteSpecificMethods {

	String screenName = "Open Source";

	public Blog clickOpenSources() throws Exception {
		try {
			driver.findElementByLinkText("Open Source").click();
			explicitlyWait();
			takeScreenshots(screenName);

			System.out.println("Open Source Success");
			reportStep("Open Source navigation is successful", "pass");
		} catch (Exception e) {
			reportStep("Open Source navigation is unsuccessful", "fail");
		}
		return new Blog();
	}
}
