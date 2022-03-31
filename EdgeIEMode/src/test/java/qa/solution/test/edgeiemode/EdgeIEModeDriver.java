package qa.solution.test.edgeiemode;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

public class EdgeIEModeDriver implements Driver {

	@Override
	public WebDriver getDriver() {

		System.setProperty("webdriver.ie.driver", "./src/test/resources/driver/IEDriverServer.exe");

		InternetExplorerOptions ieOptions = new InternetExplorerOptions();
		ieOptions.attachToEdgeChrome();
		ieOptions.ignoreZoomSettings();
		ieOptions.introduceFlakinessByIgnoringSecurityDomains();
		ieOptions.disableNativeEvents();
		ieOptions.requireWindowFocus();
		//ieOptions.destructivelyEnsureCleanSession();
		ieOptions.withEdgeExecutablePath("C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe");
		
		WebDriver driver = new InternetExplorerDriver(ieOptions);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}

}
