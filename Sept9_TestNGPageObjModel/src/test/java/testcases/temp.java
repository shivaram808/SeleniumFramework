package testcases;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class temp {
	public WebDriver driver;
	public String Browser="chrome";
	public ExtentReports reporter;
	public ExtentTest l;
	@Test
	public void testcase1() throws Throwable{
		reporter=new ExtentReports("E:\\SeleniumWorkSpace_UHG_Hyd\\"
		+ "SeleniumWebDriver_Project_UHG\\src\\com\\reports\\ExtentReports_ActiTime.html");
		l=reporter.startTest("ActimeApplication");
		
		SoftAssert st=new SoftAssert();
		if(Browser.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			 driver=new ChromeDriver(); // OpenBrowser
			 l.log(LogStatus.INFO, "Chrome Browser is lauched...");
		}else if(Browser.equalsIgnoreCase("ie")){
			System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
				 driver=new InternetExplorerDriver();
				 l.log(LogStatus.INFO, "IE Browser is lauched...");
		}else if(Browser.equalsIgnoreCase("mozilla")){
			System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
			 driver=new FirefoxDriver();
			 l.log(LogStatus.INFO, "mozilla Browser is lauched...");
		}
		driver.get("http://localhost:9000/login.do"); // open url
		 l.log(LogStatus.INFO, "URL is Opened...");
		driver.manage().window().maximize(); // maximize the window
		 l.log(LogStatus.INFO, "Browser is maximized...");
		//Implicit wait
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		 l.log(LogStatus.INFO, "Implicit wait timeout is set...");
		//Login
		WebElement username = driver.findElement(By.xpath(".//*[@id='loginFormContainer']/tbody/tr[1]/td/table/tbody/tr[1]/td[2]/input"));
		username.sendKeys("admin");
		 l.log(LogStatus.INFO, "username is entered...");
		driver.findElement(By.xpath("//input[@name='pwd']")).sendKeys("manager");
		 l.log(LogStatus.INFO, "password is entered...");
		driver.findElement(By.xpath(".//*[@id='loginButton']")).click();
		 l.log(LogStatus.INFO, "Clicked on loginnow button");
		//click on task
		driver.findElement(By.xpath(".//*[@id='topnav']/tbody/tr[1]/td[5]/a/img")).click();
		 l.log(LogStatus.INFO, "Clicked on task button");
		//click on pro and customer
		driver.findElement(By.xpath(".//*[@id='topnav']/tbody/tr[1]/td[5]/div/table/tbody/tr/td[6]/nobr/a")).click();
		 l.log(LogStatus.INFO, "Clicked on pro and customer ");
		//click on create new customer
		driver.findElement(By.xpath(".//*[@id='customersProjectsForm']/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr/td[2]/input[1]")).click();
		 l.log(LogStatus.INFO, "clicked on create new customer ");
		//createcustomer
		driver.findElement(By.xpath(".//*[@id='container']/form/table/tbody/tr[3]/td/table/tbody/tr[5]/td[2]/table/tbody/tr[1]/td[3]/input")).sendKeys("CustomerA");
		 l.log(LogStatus.INFO, "Entered customer name ");
		driver.findElement(By.xpath(".//*[@id='container']/form/table/tbody/tr[3]/td/table/tbody/tr[5]/td[2]/table/tbody/tr[2]/td[3]/textarea")).sendKeys("DescriptionA");
		 l.log(LogStatus.INFO, "Entered Description... ");
		driver.findElement(By.xpath(".//*[@id='active_customers_action']")).click();
		 l.log(LogStatus.INFO, "Selected radio button");
		driver.findElement(By.xpath(".//*[@id='container']/form/table/tbody/tr[8]/td/input[1]")).click();
		 l.log(LogStatus.INFO, "Clicked on create customer");
		//verifysucessmsg
		//String text = driver.findElement(By.xpath(".//*[@id='SuccessMessages']/tbody/tr/td/span")).getText();
		//System.out.println("text="+text);
		try{
		driver.findElement(By.xpath(".//*[@id='SuccessMessages']"
				+ "/tbody/tr/td/span")).isDisplayed();
			File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFile,new File("E:\\SeleniumWorkSpace_UHG_Hyd"
					+ "\\SeleniumWebDriver_Project_UHG\\src\\com\\Test1_Pass.bmp"),true);
		String p = l.addScreenCapture("E:\\SeleniumWorkSpace_UHG_Hyd\\SeleniumWebDriver_Project_UHG\\src\\com\\Test1_Pass.bmp");
		
		l.log(LogStatus.PASS, "Success msg dispalyed...",p);
		
		//click on logout
		driver.findElement(By.xpath(".//*[@id='logoutLink']")).click();
		 l.log(LogStatus.INFO, "Clicked on Logout");
		}catch(Throwable t){
			st.fail("Success msg not displayed...");
			File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFile,new File("E:\\SeleniumWorkSpace_UHG_Hyd"
					+ "\\SeleniumWebDriver_Project_UHG\\src\\com\\Test1_Fail.bmp"),true);
			String f = l.addScreenCapture("E:\\SeleniumWorkSpace_UHG_Hyd"
					+ "\\SeleniumWebDriver_Project_UHG\\src\\com\\Test1_Fail.bmp");
			
			l.log(LogStatus.FAIL, "Success msg not dispalyed...",f);
			//click on logout
			driver.findElement(By.xpath(".//*[@id='logoutLink']")).click();
			 l.log(LogStatus.INFO, "Clicked on Logout");
			//click on cancel creation
			driver.findElement(By.xpath(".//*[@id='DiscardChangesButton']")).click();
			 l.log(LogStatus.INFO, "Clicked on Cancel creation");
		}
		
		//close browser
		driver.quit();
		 l.log(LogStatus.INFO, "closed Browser....");
		 l.log(LogStatus.INFO, "==================================Test1 is completed.=========================================");
		 reporter.endTest(l);
		 reporter.flush();
		 st.assertAll();
		
	}

}
