package selenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import selenium.Pages;
import selenium.utils.CommonConstants;

public class IntoTutorialPage extends Pages {

    public IntoTutorialPage(final WebDriver driver) {
        super(driver);
    }

    public void clickOnTutorialNext() {
        clickOnElement(CommonConstants.XPATH_TEXT_NEXT);
    }

    public void clickOnTutorialBack() {
        if(isElementPresent(By.xpath(CommonConstants.XPATH_TEXT_PREVIOUS)))
            clickOnElement(CommonConstants.XPATH_TEXT_PREVIOUS);
        else
            clickOnElement(CommonConstants.XPATH_TEXT_HOME);
    }
}