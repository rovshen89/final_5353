
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;
import java.util.Iterator;
import java.util.Set;


public class Navigation {
    public static void main(String[] args) throws InterruptedException {
        //We have to set Chromedriver.exe to our project then create an object with driver;
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();

        //After creating driver we will navigate to the website page with get() method;
        driver.get("https://www.na.edu/");
        driver.manage().window().maximize(); // maximize window to fit full screen

        driver.getTitle(); //get page title

        /*We will define script to perform our test case TestID2*/

        Actions mouseOver = new Actions(driver);
        Thread.sleep(3000l);
        mouseOver.moveToElement(driver.findElement(By.xpath("//*[@id='mbaH2']/a"))).click().build().perform();

        //After successfully navigating to the respective page we will navigate back to main page:
        Thread.sleep(5000l);
        driver.navigate().back();
        Thread.sleep(3000l);

        /*We will define script to perform our test case TestID3*/

        //locate 365Portal web element to click on it:
        driver.findElement(By.cssSelector("#menu-item-103 > a > span")).click();

        /*After clicking to 365 Portal link our browser opens a new tab.
        In Selenium by default code runs only in parent window. In order
        to switch to a child window we have to save our window ID's to a data set*/

        //Set your window ID's to a string data set with getWindowHandles() method:
        Set<String> windowIDs = driver.getWindowHandles();

        /*After setting our ID's in data sets we have to Iterate through window ID's
        starting with parent page continuing to child page and set these ID's to
        a new created Strings accordingly. */

        Iterator<String> iterator = windowIDs.iterator();
        String parentWindow = iterator.next();

        //We can print parent page title to our console to  check weather we are in a current window:
        System.out.println("Current parent Window");
        System.out.println(driver.getTitle());

        String childWindow = iterator.next();
        //After defining child page ID we have to switch to a child window with switchTo()
        // method and pass our string value:
        driver.switchTo().window(childWindow);

        //We will print child page title to our console in order to check our active window:
        System.out.println("After switching to child window:");
        System.out.println(driver.getTitle());

        //Locate input element and enter your username then click to submit;
        driver.findElement(By.cssSelector("[id='i0116']")).sendKeys("Your username");
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        //Locate input element and enter your password then click to submit;
        Thread.sleep(3000L);
        driver.findElement(By.cssSelector("[id='i0118']")).sendKeys("Your password");
        driver.findElement(By.xpath("//input[@id='idSIButton9']")).click();
        driver.findElement(By.id("KmsiCheckboxField")).click();

        Thread.sleep(3000L);
        driver.findElement(By.xpath("//input[@id='idSIButton9']")).click();

        //Navigate to All Applications section;
        Thread.sleep(3000L);
        driver.findElement(By.id("explore-your-apps")).click();

        /*Following code was written in order to successfully click to an element,
        * in our case Moodle.*/
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
        System.out.println(driver.getTitle());

    }
}
