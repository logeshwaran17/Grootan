package pages;

import java.io.File;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.reporters.EmailableReporter;

import base.SiteSpecificMethods;

public class Home extends SiteSpecificMethods {

	String screenName = "Home";

	public Services clickHome() throws Exception {

		try {
			driver.findElementByLinkText("Home").click();
			takeScreenshots(screenName);

			System.out.println("Home Success");

			reportStep("Home tab navigation is successful", "pass");
		} catch (Exception e) {

			reportStep("Home tab navigation is unsuccessful", "fail");
		}
		return new Services();
	}
}
