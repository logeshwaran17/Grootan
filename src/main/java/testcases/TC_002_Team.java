package testcases;

import java.io.File;
import java.io.IOException;

import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.SiteSpecificMethods;
import pages.Team;

public class TC_002_Team extends SiteSpecificMethods {
	int tcNum=2;
	@BeforeTest
	public void setDetails() {
		testCaseName=this.getClass().getSimpleName();
		testDescription="Working with Team tab and comparing the members";
		

	}
	
	
	@Test
	public void teamsTask() throws Exception {
		
		
		new Team()
		.compareTeamMembers()
		.printJuniorEngg();

	}
	@AfterTest
	public void getExcelReport() throws IOException {
		
		
		fileExcel(tcNum,testCaseName);

}
}
