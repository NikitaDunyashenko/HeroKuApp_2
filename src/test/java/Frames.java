import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Frames {

    @Test
    public void framesTest() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://the-internet.herokuapp.com/frames");

        WebElement iFrame = driver.findElement(By.xpath("//a[text()='iFrame']"));
        iFrame.click();

        driver.switchTo().frame("mce_0_ifr");
        WebElement text = driver.findElement(By.xpath("//p[text()='Your content goes here.']"));
        Assert.assertEquals(text.getText(), "Your content goes here.", "Texts should be the same");
        driver.switchTo().defaultContent();

        driver.quit();
    }
}
