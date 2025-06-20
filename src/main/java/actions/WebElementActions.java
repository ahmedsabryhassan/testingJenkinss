package actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class WebElementActions {

    public static void clickBtn(By by) {
        var btn = waitUntil(by, "presenceOfElement");
        if (btn != null)
            btn.click();
    }

    public static void clickBtn(WebElement btn) {
        btn.click();
    }

    public static void setText(By textField, String text){
        WebDriverActions.driver.findElement(textField).sendKeys(text);
    }

    public static WebElement waitUntil(By b, String condition) {
        try {
            switch (condition) {
                case "presenceOfElement":
                    return (new WebDriverWait(WebDriverActions.driver, Duration.ofSeconds(10))).until(ExpectedConditions.presenceOfElementLocated(b));
                case "elementToBeClickable":
                    return (new WebDriverWait(WebDriverActions.driver, Duration.ofSeconds(10))).until(ExpectedConditions.elementToBeClickable(b));
                default:
                    Assert.fail("Wrong condition");
                    return null;
            }
        } catch (Exception e) {
            // Assert.fail("Couldn't find the element because of " + e.getMessage());
            return null;
        }
    }

    public static List<WebElement> getElements(By b) {
        return WebElementActions.waitUntil(b,"presenceOfElement") == null? null : WebDriverActions.driver.findElements(b);
    }

    public static WebElement getElement(By b) {
        return WebElementActions.waitUntil(b,"presenceOfElement");
    }
}