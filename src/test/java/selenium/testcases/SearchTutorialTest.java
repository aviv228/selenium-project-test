package selenium.testcases;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import org.apache.log4j.Logger;
import selenium.configurations.HeaderData;
import selenium.configurations.Parallelized;
import selenium.driver.WebDriverConfig;
import selenium.pageobjects.*;
import selenium.utils.CommonConstants;
import selenium.utils.WebDriverProvider;

import java.util.LinkedList;

@RunWith(Parallelized.class)
public class SearchTutorialTest {

    private static final Logger log = Logger.getLogger(SearchTutorialTest.class);
    private static final WebDriverConfig webDriverConfig = new WebDriverConfig();
    private final WebDriverProvider webDriverProvider = new WebDriverProvider(webDriverConfig);

    private WebDriver getDriver() {
        return this.webDriverProvider.getDriver();
    }

    private String browserVersion;


    @Parameterized.Parameters
    public static LinkedList<String[]> getEnvironments() throws Exception {
        LinkedList<String[]> env = new LinkedList<>();
        log.debug("Here we can add more browsers");
        log.debug("Each addition in the list will cause another thread run");
        env.add(new String[]{webDriverConfig.getBrowserName()});
        return env;
    }

    public SearchTutorialTest(String browserName) {
        this.browserVersion = browserName;
    }

    private StartPage startPage = PageFactory.initElements(getDriver(), StartPage.class);
    private HeaderData headerData = PageFactory.initElements(getDriver(), HeaderData.class);
    private TutorialPage tutorialPage = PageFactory.initElements(getDriver(), TutorialPage.class);
    private IntoTutorialPage intoTutorialPage = PageFactory.initElements(getDriver(), IntoTutorialPage.class);

    @Before
    public void setup() {
        startPage.open();
    }

    private final String content = headerData.getContentString();
    private final String subContent = headerData.getSubContentString();

    @Test
    public void testFlow() {
        log.debug("going to verify the correct page");
        tutorialPage.verifyTitle(CommonConstants.W3_SCHOOLS_ONLINE_WEB_TUTORIALS);
        log.debug("go to Tutorial page and then check it's displayed");
        tutorialPage.goToTutorial();
        tutorialPage.checkTutorialPanelIsDisplayed();
        log.debug("go to specific tutorial according user inputs");
        tutorialPage.clickOnSpecificTutorial(content, subContent);
        log.debug("click on Next page of the tutorial");
        intoTutorialPage.clickOnTutorialNext();
        log.debug("click on Previous/Home page of the tutorial");
        intoTutorialPage.clickOnTutorialBack();
        intoTutorialPage.clickOnTutorialBack();
    }

    @After
    public void closeBrowser() {
        getDriver().quit();
    }
}