import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FileDownload {

    public boolean isFileExist(File file) {
        return file.exists();
    }

    @Test
    public void fileDownloadTest() throws InterruptedException {

        WebDriverManager.chromedriver().setup();

        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", System.getProperty("user.dir"));
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        WebDriver driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://the-internet.herokuapp.com/download");

        List<WebElement> filesToDownload = driver.findElements(By.cssSelector("[href^=download]"));
        WebElement screenshot1 = filesToDownload.get(6);
        screenshot1.click();

        Thread.sleep(10000);

        String filePath = System.getProperty("user.dir") + "/SEO Raning Factor.txt";
        File file = new File(filePath);

        Assert.assertTrue(isFileExist(file));

        driver.quit();

    }
}
