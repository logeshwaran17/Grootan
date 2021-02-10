package testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.SiteSpecificMethods;
import pages.Team;

public class TC_002_Team extends SiteSpecificMethods {
	
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

}
