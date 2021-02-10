package pages;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.tools.ant.taskdefs.WaitFor.Unit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.SiteSpecificMethods;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Blog extends SiteSpecificMethods {

	String screenName = "Blog";

	public Team clickBlog() throws Exception {

		try {
			driver.findElementByLinkText("Blog").click();
			Set<String> windowHandles = driver.getWindowHandles();

			ArrayList<String> windows = new ArrayList<String>(windowHandles);
			driver.switchTo().window(windows.get(1));

			explicitlyWait();
			takeScreenshots(screenName);

			driver.close();
			driver.switchTo().window(windows.get(0));
			System.out.println("Blog Success");
			reportStep("Navigation to Blog tab is successful", "pass");
		} catch (Exception e) {
			reportStep("Navigation to Blog tab is unsuccessful", "fail");
		}
		return new Team();
	}

}
