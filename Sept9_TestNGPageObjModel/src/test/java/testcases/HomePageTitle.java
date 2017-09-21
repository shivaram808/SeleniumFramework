package testcases;

import org.openqa.selenium.support.PageFactory;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import reusables.BaseClass;

public class HomePageTitle extends BaseClass{

	
	@BeforeMethod
	
	public void before()
	{
		if(!ht.get("Validate_Title").equalsIgnoreCase("Yes"))
		{
			throw new SkipException("Validate_Title : This Test case not set for Execution......");
		}
		
		logger = report.startTest("Validating Home page title........");	
	}
	
	@Test
	public void verify_HPTitle()
		{
		pageObjects.HomePage hp = PageFactory.initElements(driver,pageObjects.HomePage.class);
		
		String Act_Title = hp.HP_Title();
		String Exp_Title = "Automation Techno - Home";
		
		st.assertEquals(Exp_Title, Act_Title,"Tite is incorrect!!");
		logger.log(LogStatus.PASS,"Home page title verified  :  "+Act_Title);			
		st.assertAll();
		
		}
	
@AfterMethod
	
	public void After()
	{
		report.endTest(logger);
	}
	

}
