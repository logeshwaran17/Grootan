package pages;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import base.SiteSpecificMethods;

public class ContactUs extends SiteSpecificMethods {

	String screenName = "Contact Us";

	public Home clickContactUs() throws Exception {
		try {
			driver.findElementByLinkText("Contact Us").click();
			explicitlyWait();
			takeScreenshots(screenName);

			System.out.println("Contact Us Success");
			reportStep("Navigation to Contact Us tab is successful", "pass");
		} catch (Exception e) {
			reportStep("Navigation to Contact Us tab is successful", "fail");
		}
		return new Home();
	}
}
