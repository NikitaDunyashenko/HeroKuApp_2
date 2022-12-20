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
        try {
            inputText.sendKeys("Teach Me Skills");
        } catch (ElementNotInteractableException e){
            System.out.println("Element is not enabled");
        }

        Assert.assertEquals(inputText.getText(), "");

        WebElement enableButton = driver.findElement(By.xpath("//button[text()='Enable']"));
        enableButton.click();
        WebDriverWait wait1 = new WebDriverWait(driver, 15);
        wait1.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("[id=message]"))));

        inputText.sendKeys("Teach Me Skills");
        Assert.assertEquals(inputText.getAttribute("type"), "text");

        driver.quit();

    }
}
