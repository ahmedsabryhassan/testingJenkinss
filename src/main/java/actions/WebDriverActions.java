package actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverActions {
    public static WebDriver driver;

    public static void initializeWebDriver()
    {
        System.setProperty("webdriver.chrome.driver", "C:\\driver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new", "--no-sandbox", "--disable-gpu");
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
