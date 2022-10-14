import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.swing.*;
import java.time.Duration;
import java.util.List;

public class Task2Tests {

    @Test
    public void testcase1(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://demoqa.com/progress-bar");
        driver.manage().window().maximize();

        WebElement startBtn=driver.findElement(By.id("startStopButton"));
        WebElement progress_bar=driver.findElement(By.className("progress"));
        startBtn.click();
        boolean x= new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.textToBePresentInElement(progress_bar, "100%"));
        Assert.assertTrue(x);
        System.out.println("100%");

        driver.quit();
    }
    @Test
    public void testcase2(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://webdriveruniversity.com/Dropdown-Checkboxes-RadioButtons/index.html ");
        driver.manage().window().maximize();
        Actions action = new Actions(driver);

    // - Choose programming language from dropdown and check that it was selected
        WebElement python= driver.findElement(By.xpath("//option[@value='python']"));
        WebElement prog_lang=driver.findElement(By.id("dropdowm-menu-1"));
        // select class object
        Select choose_prog_lang=new Select(prog_lang);
        //choose programing language python
        choose_prog_lang.selectByVisibleText("Python");
        //check
        Assert.assertTrue(python.isSelected());
    //- Click on all unselected checkboxes
        List<WebElement> checkBoxes= driver.findElements(By.xpath("//input[@type='checkbox']"));

        checkBoxes.forEach(element -> {if (!element.isSelected()) {element.click();}} );

    //- Click on 'Purple' radio button
        List<WebElement> radioBtn=driver.findElements(By.xpath("//input[@name='color']"));
        //radioBtn.forEach(WebElement::click);
        radioBtn.forEach(webElement -> {if (webElement.getAttribute("value").equalsIgnoreCase("purple")) {
                webElement.click(); }} );

    //- In 'Selected & Disabled' section check that 'Orange' option in dropdown is disabled

        WebElement orange_select= driver.findElement(By.xpath("//option[@value='orange']"));
        Assert.assertFalse(orange_select.isEnabled());

        driver.quit();

    }

}
