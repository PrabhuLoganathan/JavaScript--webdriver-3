import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

public class EcommSitesTest {
    private static final String SELENIUM_GRID_URL = "http://localhost:4444/wd/hub";

    private WebDriver webDriver;

    @BeforeMethod
    public void startWebDriver(Method method) throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);
        desiredCapabilities.setCapability(CapabilityType.PLATFORM, Platform.LINUX);
        desiredCapabilities.setCapability("name", method.getName());

        webDriver = new RemoteWebDriver(new URL(SELENIUM_GRID_URL), desiredCapabilities);

        webDriver.manage().window().maximize();
    }

    @AfterMethod
    public void quitBrowser() {
        webDriver.quit();
    }

    @Test
    public void loadZalandoPageAndCheckTitle() {

        // Go to the homepage
        webDriver.get("http://www.zalando.de");

        // Assert that the title is the expected one
        Assert.assertEquals(webDriver.getTitle(), "Schuhe, Mode und Accessoires online kaufen | Schnelle Lieferung von Zalando",
                "Page title is not the expected one");
    }

    @Test
    public void loadAmazonPageAndCheckTitle() {

        // Go to the homepage
        webDriver.get("http://www.amazon.de");

        // Assert that the title is the expected one
        Assert.assertEquals(webDriver.getTitle(), "Amazon.de: Günstige Preise für Elektronik & Foto, Filme, " +
                "Musik, Bücher, Games, Spielzeug & mehr");
    }

    @Test
    public void loadOttoPageAndCheckTitle() {

        // Go to the homepage
        webDriver.get("http://www.otto.de");

        // Assert that the title is the expected one
        Assert.assertEquals(webDriver.getTitle(), "OTTO - Mode, Möbel & Technik » Zum Online-Shop",
                "Page title is not the expected one");
    }

}
