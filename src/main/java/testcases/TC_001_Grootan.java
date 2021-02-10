package testcases;

import org.testng.annotations.Test;

import base.SiteSpecificMethods;
import pages.Home;

import java.io.File;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

public class TC_001_Grootan extends SiteSpecificMethods {

	@BeforeTest
	public void setDetails() throws IOException, Exception {
		testCaseName=this.getClass().getSimpleName();
		testDescription="Navigating to each tab and getting screenshots";
		

	} 	
	
	
	@Test(invocationCount = 2)
	public void runTest() throws Exception {
	
		new Home()
		.clickHome()
		.clickServices()
		.clickOpenSources()
		.clickBlog()
		.clickTeam()
		.clickCareers()
		.clickContactUs();
	}

}
