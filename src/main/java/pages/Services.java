package pages;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import base.SiteSpecificMethods;

public class Services extends SiteSpecificMethods {

	String screenName = "Services";

	public OpenSource clickServices() throws Exception {
		try {
			driver.findElementByLinkText("Services").click();
			explicitlyWait();

			takeScreenshots(screenName);

			System.out.println("Services Success");
			reportStep("Navigation to Services tab is successful", "pass");
		} catch (Exception e) {
			reportStep("Navigation to Services tab is unsuccessful", "fail");
		}
		return new OpenSource();
	}
}
