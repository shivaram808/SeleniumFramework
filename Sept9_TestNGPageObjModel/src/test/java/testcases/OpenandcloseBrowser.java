package testcases;

import java.io.IOException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;

import reusables.BaseClass;

public class OpenandcloseBrowser extends BaseClass{
	
	@BeforeSuite
	public void Beforesuite() throws Throwable
	{
	
		initializedriver();
		
		moduledriver() ;
	}

	@AfterSuite
	public void Aftersuite()
	{
		report.flush();
		driver.get("C:\\Users\\Shiva Ram\\workspaceEXT\\Sept9_TestNGPageObjModel\\Test_IP_OP\\Report.html");
	}
}
