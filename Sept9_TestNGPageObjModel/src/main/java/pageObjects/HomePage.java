package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	public WebDriver driver;
	
	 public HomePage(WebDriver driver)
	 {
		 this.driver = driver;
	 
	 }

	@FindBy(xpath=".//*[text()='Automation Techno']")
	WebElement HP_Header;
	
	
	public String HP_Title()
	{
		String title = driver.getTitle();
		return title;
	}
	
	
	public String HP_Header()
	{
		String HPheader = HP_Header.getText();
		return HPheader;
	}
	
}
