package selenium;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import selenium.configurations.TypedProperties;

public abstract class SeleniumFunctions {

	protected WebDriver driver;

	public SeleniumFunctions(final WebDriver driver) {
		this.driver = driver;
	}

	private static final int ELEMENT_WAIT_TIMEOUT_IN_SECONDS = 5;

	private static final int PAGE_WAIT_TIMEOUT_IN_SECONDS = 60;

	TypedProperties testData = new TypedProperties("/test_data.properties");

	protected String getTestData(String key) {
		return testData.getValue(key);
	}

	protected boolean isElementPresent(final By by) {
		return this.driver.findElements(by).size() > 0;
	}

	protected boolean isElementPresent(final WebElement element) {
		try {
			element.getTagName();
		} catch (final NoSuchElementException ignored) {
			return false;
		} catch (final StaleElementReferenceException ignored) {
			return false;
		}
		return true;
	}

	protected boolean isElementVisible(final By by) {
		return this.driver.findElement(by).isDisplayed();
	}

	protected boolean isElementVisible(final WebElement element) {
		return element.isDisplayed();
	}

	protected boolean isAnyTextPresent(final By by) {
		final String text = this.driver.findElement(by).getText();
		return StringUtils.isNotBlank(text);
	}

	protected boolean isAnyTextPresent(final WebElement element) {
		final String text = element.getText();
		return StringUtils.isNotBlank(text);
	}

	protected void waitForElement(final WebElement element) {
		this.waitForElement(element, ELEMENT_WAIT_TIMEOUT_IN_SECONDS);
	}

	protected void waitForElement(final WebElement element, final int timeoutInSeconds) {
		final WebDriverWait wait = new WebDriverWait(this.driver, timeoutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	protected void waitForElement(final By by) {
		waitForElement(by, ELEMENT_WAIT_TIMEOUT_IN_SECONDS);
	}

	protected void waitForElement(final By by, final int timeoutInSeconds) {
		final WebDriverWait wait = new WebDriverWait(this.driver, timeoutInSeconds);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	protected void waitForElementIsInvisible(final By by) {
		final WebDriverWait wait = new WebDriverWait(this.driver, ELEMENT_WAIT_TIMEOUT_IN_SECONDS);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
	}

    protected void waitForTextToAppear(final String textToAppear, final WebElement element) {
        final WebDriverWait wait = new WebDriverWait(this.driver, ELEMENT_WAIT_TIMEOUT_IN_SECONDS);
        wait.until(ExpectedConditions.textToBePresentInElement(element, textToAppear));
    }

    protected void waitForUrlPart(final String urlPart) {
        final WebDriverWait wait = new WebDriverWait(this.driver, ELEMENT_WAIT_TIMEOUT_IN_SECONDS);
        wait.until(ExpectedConditions.urlContains(urlPart));
    }

    protected void waitForPageLoad() {
		waitForPageLoad(PAGE_WAIT_TIMEOUT_IN_SECONDS);
	}

    protected void waitForPageLoad(final int timeoutInSeconds) {
		waitForElement(By.tagName("html"), timeoutInSeconds);
	}

	protected String xpathFinder(final String... xpathList) {
		for (final String xpath : xpathList) {
			if (isElementPresent(By.xpath(xpath))) {
				return xpath;
			}
		}
		return null;
	}

	protected String getTitleName(){
	    return this.driver.getTitle();
    }

	protected void clickAndWait(final String locator) {
		final WebElement element = findElementByLocator(locator);
		element.click();
		waitForPageLoad();
	}

    protected void clickOnElement(final String locator){
        clickAndWait(locator);
    }


    protected String getStyleAttribute(final String locator){
        return findElementByLocator(locator).getAttribute("style");
    }


    protected WebElement findElementByLocator(final String locator) {
		return this.driver.findElement(buildByUsingLocator(locator));
	}

    protected By buildByUsingLocator(String locator) {
		By by;
		locator = locator.trim();
		if (locator.startsWith("id=")) {
			by = By.id(locator.replace("id=", ""));
		} else if (locator.startsWith("//") || locator.startsWith("xpath=")) {
			by = By.xpath(locator.replace("xpath=", ""));
		} else if (locator.startsWith("css=#") || locator.startsWith("img")) {
			by = By.cssSelector(locator.replace("css=", ""));
		} else if (locator.startsWith("css=") || locator.startsWith("img")) {
			by = By.cssSelector(locator.replace("css=", ""));
		} else if (locator.startsWith("link=")) {
			by = By.linkText(locator.replace("link=", ""));
		} else if (locator.startsWith("name=")) {
			by = By.name(locator.replace("name=", ""));
		} else if (locator.startsWith("class=")) {
			by = By.className(locator.replace("class=", ""));
		} else if (locator.startsWith("tagName=")) {
			by = By.tagName(locator.replace("tagName=", ""));
		} else {
			by = By.id(locator);
		}
		return by;
	}



}
