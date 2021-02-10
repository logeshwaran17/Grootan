package base;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;
import org.apache.hc.core5.util.Asserts;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;
//import utils.ReadExcel;

public class SiteSpecificMethods {

	public static ChromeDriver driver;
	public String excelFileName;
	public static String screenName;
	public static ExtentHtmlReporter reporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentTest node;
	public String testCaseName, testDescription;
	public static FileOutputStream f;
	
	
	public static int folderCount = 1;

	@BeforeSuite
	public void startReport() throws IOException, Exception {
		
		f=new FileOutputStream(new File("./ExtentReports/TSR.xlsx"));
		XSSFWorkbook head=new XSSFWorkbook();
		XSSFCellStyle headStyle = head.createCellStyle();
		headStyle.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
		headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		XSSFRow headRow = head.createSheet("TSR").createRow(0);
		XSSFCell tcHead = headRow.createCell(0);
		tcHead.setCellValue("TEST CASE NAME");
		tcHead.setCellStyle(headStyle);
		XSSFCell stsHead = headRow.createCell(1);
		stsHead.setCellValue("STATUS");
		stsHead.setCellStyle(headStyle);
		
		head.write(f);
		head.close();
		
		reporter = new ExtentHtmlReporter(".//ExtentReports//result.html");

		reporter.setAppendExisting(true);
		reporter.config().setDocumentTitle("Grootan Automation Task Report");
		reporter.config().setReportName("Basic Navigation");
		reporter.config().setTheme(Theme.STANDARD);
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Browser Automated", "Chrome");
		extent.setSystemInfo("Test Executor Name", "Logeshwaran A");

	}

	@BeforeClass
	public void testDetails() {
		test = extent.createTest(testCaseName, testDescription);

	}

	@BeforeMethod
	public void startBrowser() throws FileNotFoundException {
		
		node = test.createNode(testCaseName);
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://grootan.com");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@AfterMethod
	public void closeBrowser() {
		
		System.out.println(folderCount);
		++folderCount;

		driver.close();

	}

	@AfterSuite()
	public void endReport() throws IOException {
		extent.flush();
		f.close();

	}

	public String takeSnapForReports() throws IOException {
		SimpleDateFormat df = new SimpleDateFormat("MMddyyyy hhmmss");
		String time = df.format(new Date());

		File src = driver.getScreenshotAs(OutputType.FILE);

		FileUtils.copyFile(src, new File("./ReporterScreenshots/image" + time + ".png"));
		return time;

	}

	public void takeScreenshots(String screenName) throws IOException {
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File target = new File("./Folder" + folderCount + "/" + screenName + ".png");
		FileUtils.copyFile(source, target);

	}

	public void reportStep(String msg, String status) throws IOException {
		if (status.equalsIgnoreCase("pass")) {
			node.pass(msg, MediaEntityBuilder
					.createScreenCaptureFromPath(".././ReporterScreenshots/image" + takeSnapForReports() + ".png")
					.build());
		} else {
			node.fail(msg, MediaEntityBuilder
					.createScreenCaptureFromPath(".././ReporterScreenshots/image" + takeSnapForReports() + ".png")
					.build());
		}

	}
	public static void fileExcel(int m,String tcName) throws IOException {
		XSSFWorkbook book=new XSSFWorkbook("./ExtentReports/TSR.xlsx");
		XSSFCellStyle pass = book.createCellStyle();
		
		pass.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
		pass.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		XSSFCellStyle fail = book.createCellStyle();
		
		fail.setFillForegroundColor(IndexedColors.RED.getIndex());
		fail.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		XSSFSheet tsrSheet = book.getSheet("TSR");
		XSSFRow tc1Row = tsrSheet.createRow(m);
		tc1Row.createCell(0).setCellValue(tcName);
		if (tc1Row.getCell(0).getStringCellValue().equalsIgnoreCase(tcName)) {
			tc1Row.createCell(1).setCellStyle(pass);
			tc1Row.createCell(1).setCellValue("PASS");
				
		}
		else {
			tc1Row.createCell(1).setCellStyle(fail);
			tc1Row.createCell(1).setCellValue("FAIL");
			
			
		}
		
		book.write(f);
		book.close();
	}
	public void explicitlyWait() throws InterruptedException {
		Thread.sleep(3000);

		/*
		 * WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		 * wait.until(new Function<WebDriver, Boolean>() {
		 * 
		 * @Override public Boolean apply(WebDriver t) { t = driver; JavascriptExecutor
		 * js = (JavascriptExecutor) driver;
		 * 
		 * return (Boolean) (js.executeScript("return arguments[0].complete;", driver));
		 * } });
		 */

	}

}
