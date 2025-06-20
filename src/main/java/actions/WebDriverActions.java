package actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverActions {
    public static WebDriver driver;

    public static void initializeWebDriver()
    {
        WebDriverManager.chromedriver().setup(); // this downloads the correct driver
        driver = new ChromeDriver();
    }
    public static void maximizeScreen(){
        driver.manage().window().maximize();
    }

    public static void navigateToPage (String url){
        driver.get(url);
    }

    public static void closeDriver(){
        driver.close();
    }

    public static void quitDriver(){
        driver.quit();
    }
}
