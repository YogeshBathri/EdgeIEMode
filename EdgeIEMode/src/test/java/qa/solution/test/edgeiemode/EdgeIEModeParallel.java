package qa.solution.test.edgeiemode;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.google.common.io.Files;

public class EdgeIEModeParallel {

	private void jsClick(WebDriver driver, WebElement ele) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", ele);
	}

	private void jsSetValue(WebDriver driver, WebElement ele, String value) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].value='" + value + "';", ele);
	}
	
	private synchronized void takescreenshot(WebDriver driver, String name) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dst = new File("./src/test/resources/screenshot/"+name+".jpg");
		
		Files.copy(src, dst);
		
		
	}
	
	private void emptyScreenshotDir() throws IOException {
		File dirScr= new File("./src/test/resources/screenshot");
		FileUtils.cleanDirectory(dirScr);
	}

	@BeforeSuite
	public void clear() throws IOException {
		emptyScreenshotDir();
	}
	
	@Test
	public void testAmazonLogin() throws InterruptedException, IOException {
		WebDriver driver = null;
		try {
			
			driver = new EdgeIEModeDriver().getDriver();

			driver.get("https://www.amazon.in/");
			Thread.sleep(2000);

			jsClick(driver, driver.findElement(By.xpath("//a[text()='Sign in securely']")));

			jsSetValue(driver, driver.findElement(By.id("ap_email")), "8871110000");

			jsClick(driver, driver.findElement(By.id("continue")));

			Reporter.log("Success !!");
		} catch (Exception e) {
			
			e.printStackTrace();
			Assert.fail("Exception Occured !!");
		} finally {
			
			takescreenshot(driver, "screenshot1");
			driver.close();
		}
	}

	@Test
	public void testAmazonLogin1() throws InterruptedException, IOException {
		WebDriver driver = null;
		try {
			
			driver = new EdgeIEModeDriver().getDriver();

			driver.get("https://www.amazon.in/");
			Thread.sleep(2000);

			jsClick(driver, driver.findElement(By.xpath("//a[text()='Sign in securely']")));

			jsSetValue(driver, driver.findElement(By.id("ap_email")), "999900000");

			jsClick(driver, driver.findElement(By.id("continue")));

			Reporter.log("Success !!");
		} catch (Exception e) {
			
			e.printStackTrace();
			Assert.fail("Exception Occured !!");
		} finally {
			takescreenshot(driver, "screenshot2");
			driver.close();
		}

	}

}
