import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class FileUpload {

    @Test
    public void fileUploadTest() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://the-internet.herokuapp.com/upload");

        WebElement fileUpload = driver.findElement(By.id("file-upload"));
        fileUpload.sendKeys("/Users/nikita/Documents/HomeWork_Project/HeroKuApp_2/Advertising.doc");
        WebElement uploadButton = driver.findElement(By.cssSelector("#file-submit"));
        uploadButton.click();

        WebElement fileName = driver.findElement(By.xpath("//div[contains(text(),'Advertising.doc')]"));
        Assert.assertEquals(fileName.isDisplayed(), true, "fileName should be displayed");
        Assert.assertEquals(fileName.getText(), "Advertising.doc", "File names should be same");

        driver.quit();
    }
}
