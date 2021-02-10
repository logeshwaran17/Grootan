package pages;

import org.testng.AssertJUnit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import base.SiteSpecificMethods;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class Team extends SiteSpecificMethods {

	String screenName = "Team";

	public Careers clickTeam() throws Exception {

		try {
			driver.findElementByLinkText("Team").click();
			explicitlyWait();
			driver.navigate().refresh();
			takeScreenshots(screenName);

			System.out.println("Team SS Success");

			reportStep("Team tab navigation is successful", "pass");
		} catch (Exception e) {

			reportStep("Team tab navigation is unsuccessful", "fail");
		}
		return new Careers();

	}

	public Team compareTeamMembers() throws Exception {
		try {
			driver.findElementByLinkText("Team").click();
			explicitlyWait();
			driver.navigate().refresh();

			WebElement cto = driver.findElementByXPath("//h5[text()='CTO & Co-Founder']/preceding-sibling::img");
			Screenshot ctoImage = new AShot().takeScreenshot(driver, cto);
			BufferedImage ctoPic = ctoImage.getImage();

			WebElement hr = driver.findElementByXPath("//h5[text()='HR Manager']/preceding-sibling::img");
			Screenshot hrImage = new AShot().takeScreenshot(driver, hr);
			BufferedImage hrPic = hrImage.getImage();

			ImageDiffer imgDiff = new ImageDiffer();
			ImageDiff diff = imgDiff.makeDiff(ctoPic, hrPic);

			if (diff.hasDiff() == true) {

				System.out.println("CTO and HR not same");
				ImageIO.write(ctoPic, "PNG", new File(".//CTO.png"));
				ImageIO.write(hrPic, "PNG", new File(".//HR.png"));

			} else {
				ImageIO.write(ctoPic, "PNG", new File(".//CTOandHR.png"));
			}

			System.out.println("Team comparion CTO and HR Success");
			AssertJUnit.assertTrue(diff.hasDiff());

			reportStep("CTO and HR image comparison fails", "pass");
		} catch (Exception e) {
			reportStep("CTO and HR images should not be same", "fails");
		}
		return this;

	}

	public Team printJuniorEngg() throws Exception {
		try {
			FileOutputStream f = new FileOutputStream(new File("./ExtentReports/TSR.xlsx"));
			XSSFWorkbook wb = new XSSFWorkbook();
			XSSFSheet je = wb.createSheet("JUNIOR ENGINEERS");
			XSSFCellStyle cs = wb.createCellStyle();
			cs.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
			cs.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			XSSFCell headJE = je.createRow(0).createCell(0);
			headJE.setCellValue("Junior Engineers".toUpperCase());
			headJE.setCellStyle(cs);
			List<WebElement> juniorEngg = driver
					.findElementsByXPath("//*[text()='Junior Engineer']/preceding-sibling::h3");
			List<String> jnrEng = new ArrayList<String>();
			for (WebElement webElement : juniorEngg) {
				jnrEng.add(webElement.getText());

			}
			String[] juniorEngineer = jnrEng.toArray(new String[jnrEng.size()]);
			for (int i = 0; i < juniorEngineer.length; i++) {
				je.createRow(i + 1).createCell(0).setCellValue(juniorEngineer[i]);

			}
			wb.write(f);
			wb.close();
			f.close();
			
			System.out.println("Team JE export Success");

			reportStep("Junior engineers in the teams are printed", "pass");
		} catch (Exception e) {
			reportStep("Junior engineers in the teams are not printed", "fail");
		}
		return this;

	}
}
