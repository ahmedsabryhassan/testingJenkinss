package actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverActions {
    public static WebDriver driver;

    public static void initializeWebDriver()
    {
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
