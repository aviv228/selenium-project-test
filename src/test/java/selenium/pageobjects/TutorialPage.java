package selenium.pageobjects;


import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import selenium.Pages;
import selenium.utils.CommonConstants;

public class TutorialPage extends Pages {


    public TutorialPage(final WebDriver driver) {
        super(driver);
    }

    public void verifyTitle(final String title){
        Assert.assertEquals(title, getTitleName());
    }

    public void clickOnSpecificTutorial(final String content, final String subContent) {
        final String locator = String.format(CommonConstants.XPATH_FOLLOWING_SIBLING_TEXT_FORMAT, content, subContent);
        if (isElementPresent(By.xpath(locator))) {
            clickOnElement(locator);
        } else {
            Assert.fail("One of the inputs is wrong as the element wasn't found");
        }
    }

    public void checkTutorialPanelIsDisplayed() {
        final String style = getStyleAttribute(CommonConstants.NAV_TUTORIALS);
        Assert.assertEquals(style, CommonConstants.DISPLAY_BLOCK);
    }

    public void goToTutorial() {
        clickOnElement(CommonConstants.NAVBTN_TUTORIALS);
    }
}