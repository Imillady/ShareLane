import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.get("https://sharelane.com/cgi-bin/register.py");
    }

    @Test
    public void CheckPositiveSingUp() {
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        driver.findElement(By.cssSelector("[value='Continue']")).click();
        driver.findElement(By.name("first_name")).sendKeys("Tatyana");
        driver.findElement(By.name("last_name")).sendKeys("Astaf");
        driver.findElement(By.name("email")).sendKeys("test@test.by");
        driver.findElement(By.name("password1")).sendKeys("12345678");
        driver.findElement(By.name("password2")).sendKeys("12345678");
        driver.findElement(By.cssSelector("[value='Register']")).click();
        WebElement emailInput = driver.findElement(By.xpath("//table[@border='1']//b"));
        String emailAddress = emailInput.getText();
        driver.get("https://sharelane.com/cgi-bin/main.py");
        driver.findElement(By.name("email")).sendKeys(emailAddress);
        driver.findElement(By.name("password")).sendKeys("1111");
        driver.findElement(By.cssSelector("[value='Login']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        boolean elementOnPage = driver.findElement(By.cssSelector("[href='./log_out.py']")).isDisplayed();
        Assert.assertTrue(elementOnPage);
    }

    @AfterMethod(alwaysRun = true)
    public void quit() {
        driver.quit();
    }
}
