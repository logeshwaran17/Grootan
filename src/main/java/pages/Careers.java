package pages;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import base.SiteSpecificMethods;

public class Careers extends SiteSpecificMethods {

	String screenName = "Careers";

	public ContactUs clickCareers() throws Exception {
		try {
			driver.findElementByLinkText("Careers").click();
			explicitlyWait();
			takeScreenshots(screenName);

			System.out.println("Careers Success");
			reportStep("Navigation to Careers tab is successful", "pass");
		} catch (Exception e) {

			reportStep("Navigation to Careers tab is unsuccessful", "fail");
		}
		return new ContactUs();

	}
}
