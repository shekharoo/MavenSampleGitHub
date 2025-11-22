package SeleniumFiles;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class RunSelenium {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver =  new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://49.249.28.218:8098/ ");
        Thread.sleep(1000);
        driver.findElement(By.id("username")).sendKeys("rmgyantra");
        driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        System.out.println("Login is Successfull!!");
        Thread.sleep(5000);
        driver.quit();
    }
}
