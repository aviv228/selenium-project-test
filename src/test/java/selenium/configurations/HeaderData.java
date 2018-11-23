package selenium.configurations;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import selenium.Pages;

public class HeaderData extends Pages {

	public HeaderData(final WebDriver driver) {
		super(driver);
	}


	public String getContentString(){
		return getTestData("search.content");
	}

	public String getSubContentString(){
		return getTestData("search.subcontent");
	}

}