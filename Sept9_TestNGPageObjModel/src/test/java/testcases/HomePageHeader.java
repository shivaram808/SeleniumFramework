package testcases;

import org.openqa.selenium.support.PageFactory;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import reusables.BaseClass;

public class HomePageHeader extends BaseClass{
	
	
	@BeforeMethod
	
	public void before()
	{
		if(!ht.get("Validate_Header").equalsIgnoreCase("Yes"))
		{
			throw new SkipException("Validate_Header : This Test case not set for Execution......");
		}
		
		logger = report.startTest("Validating Home page Heading........");	
	}
	
	@Test
	public void verify_HPHeader()
	{	
		pageObjects.HomePage hp = PageFactory.initElements(driver,pageObjects.HomePage.class);
	
		String Act_Header = hp.HP_Header();
		String Exp_Header = "AUTOMATION TECHNO";

		if(Exp_Header.equalsIgnoreCase(Act_Header))
		{
			logger.log(LogStatus.PASS,"Homepage header verified succesfully   :    "+Act_Header);
		}
		
		else
		{
			logger.log(LogStatus.FAIL," incorrect title displayed   :    "+Act_Header);
		}
	
	}
@AfterMethod
	
	public void After()
	{
		report.endTest(logger);
	}

}
	

