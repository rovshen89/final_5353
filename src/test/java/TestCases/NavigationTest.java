package TestCases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Iterator;
import java.util.Set;

public class NavigationTest {

    @Test
    public void testID1(){
        //This is our TestID1, we will check page title;
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.get("https://www.na.edu/");
        driver.manage().window().maximize();
        String pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle, "Welcome to North American University | Houston TX");
        driver.close();
    }

    @Test
    public void TestID2(){
        //This is our TestID2, we will check page title;
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.get("https://www.na.edu/");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//*[@id='mbaH2']/a")).click();
        String sectionTitle = driver.getTitle();
        Assert.assertEquals(sectionTitle, "Headlines Archives - North American University");
        driver.close();

    }

    @Test
    public void TestID3() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.get("https://www.na.edu/");
        driver.manage().window().maximize();
        driver.findElement(By.cssSelector("#menu-item-103 > a > span")).click();
        Set<String> windowIDs = driver.getWindowHandles();
        Iterator<String> iterator = windowIDs.iterator();
        String parentWindow = iterator.next();
        String childWindow = iterator.next();
        driver.switchTo().window(childWindow);
        driver.findElement(By.cssSelector("[id='i0116']")).sendKeys("Your username"); //enter correct username
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        Thread.sleep(3000L);
        driver.findElement(By.cssSelector("[id='i0118']")).sendKeys("Your password"); //enter correct password
        driver.findElement(By.xpath("//input[@id='idSIButton9']")).click();
        driver.findElement(By.id("KmsiCheckboxField")).click();
        Thread.sleep(3000L);
        driver.findElement(By.xpath("//input[@id='idSIButton9']")).click();
        Thread.sleep(3000L);
        driver.findElement(By.id("explore-your-apps")).click();
        Thread.sleep(3000);
        WebElement mood =driver.findElement(By.xpath("//div[@class='fluent-tiles']/div[10]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", mood);
        Thread.sleep(5000);
        mood.click();
        Thread.sleep(10000);
        Set<String> windowID2 = driver.getWindowHandles();
        Iterator<String> iterator2 = windowID2.iterator();
        String parentWindow2 = iterator2.next();
        String childWindow2 = iterator2.next();
        String moodleWindow = iterator2.next();
        driver.switchTo().window(moodleWindow);
        String moodleTitle = (driver.getTitle());
        Assert.assertEquals(moodleTitle, "Dashboard");
        driver.quit();

    }

}
