import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;



import java.util.concurrent.TimeUnit;

public class DynamicControls {

    @Test
    public void dynamicControlTest() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://the-internet.herokuapp.com/dynamic_controls");

        WebElement RemoveButton = driver.findElement(By.cssSelector("[onclick^=swapCheck]"));
        RemoveButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("message"))));

        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        int numberOfElementsCheckbox = driver.findElements(By.cssSelector("[type=checkbox]")).size();
        Assert.assertEquals(numberOfElementsCheckbox, 0, "Checkbox has been deleted");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement inputText = driver.findElement(By.cssSelector("[type=text]"));
        Assert.assertEquals(inputText.isEnabled(), false);

        WebElement enableButton = driver.findElement(By.xpath("//button[text()='Enable']"));
        enableButton.click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("[id=message]"))));

        Assert.assertEquals(inputText.isEnabled(), true);
        inputText.sendKeys("Teach Me Skills");

        driver.quit();

    }
}
