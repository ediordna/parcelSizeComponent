package parcelp;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

public class UATests {
	private WebDriver driver;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		DesiredCapabilities DesireCaps = new DesiredCapabilities();
		DesireCaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, 
				System.getenv("PHANTOMJS_PATH"));
		driver = new PhantomJSDriver(DesireCaps);
		baseUrl = "http://www.allgaeu-parcel-service.de:1100/rest/size"; //angepasst
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testUntitledTestCase() throws Exception {
		driver.get(baseUrl);
		driver.findElement(By.id("cfg-ps-length")).click();
		driver.findElement(By.id("cfg-ps-length")).clear();
		driver.findElement(By.id("cfg-ps-length")).sendKeys("10");
		driver.findElement(By.id("cfg-ps-width")).clear();
		driver.findElement(By.id("cfg-ps-width")).sendKeys("10");
		driver.findElement(By.id("cfg-rs-height")).clear();
		driver.findElement(By.id("cfg-rs-height")).sendKeys("10");
		driver.findElement(By.xpath("//div[@id='btn-ps-calc-size']/h3")).click();
		Thread.sleep(5000);
		assertEquals("Paketgröße: S", driver.findElement(By.id("ps-result-size")).getText());
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
}
}