package stepdefs;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;


import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;


public class Hooks {

	public static WebDriver driver;

	@Before
	public void SetUp() {

		driver = new ChromeDriver();
	}
	
	public void takeScraenshotOnFailure(Scenario scenario) throws IOException {

			TakesScreenshot ts = (TakesScreenshot) driver;

			File src = ts.getScreenshotAs(OutputType.FILE);
			File target = new File("screenshot/" + ".png");
			try {
				FileUtils.copyFile(src, target);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	
	@After
	public void TearDown() {
		driver.quit();
	}

}
