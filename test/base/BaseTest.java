package base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

import java.io.File;

import static com.vyshnivskyi.autoOne.infrastructure.models.ConstantInstances.WEB_DRIVER;

public class BaseTest {
	private static final String AUTO_ONE_MAIN_PAGE = "https://www.auto1.com";

	@BeforeSuite
	protected void beforeSuite() {
		File chromeDriverExeFile = new File("./resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", chromeDriverExeFile.getAbsolutePath());
	}

	@AfterMethod
	protected void afterMethod() {
		WEB_DRIVER.manage().deleteAllCookies();
		WEB_DRIVER.get(AUTO_ONE_MAIN_PAGE);
	}

	@AfterTest
	protected void afterTest() {
		WEB_DRIVER.quit();
	}
}
