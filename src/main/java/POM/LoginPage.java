package POM;
import actions.WebDriverActions;
import actions.WebElementActions;
import org.openqa.selenium.By;

public class LoginPage {
    String url = "https://www.saucedemo.com/v1/";
    String usernameTextField = "user-name";
    String passwordTextField = "password";
    String loginBtn = "login-button";
    String errorMessage = "//h3[@data-test=\"error\"]";

    public void navigateToLoginPage(){
        WebDriverActions.navigateToPage(url);
    }

    public void login(String username, String password)
    {
        WebElementActions.setText(By.id(usernameTextField), username);
        WebElementActions.setText(By.id(passwordTextField), password);
        WebElementActions.clickBtn(By.id(loginBtn));
    }
    public boolean isLoggedUserSuccessful()
    {
        return WebDriverActions.driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory.html");
    }
    public boolean isLoggedUserLocked()
    {
        return WebDriverActions.driver.findElement(By.xpath(errorMessage)).getText().equals("Epic sadface: Sorry, this user has been locked out.");
    }
    public String getLoginErrorMessage(){
        return WebElementActions.getElement(By.xpath(errorMessage)).getText();
    }
}
