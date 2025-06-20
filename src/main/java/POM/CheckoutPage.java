package POM;

import org.openqa.selenium.By;
import actions.WebElementActions;

public class CheckoutPage {
    String firstNameSelector = "first-name";
    String lastNameSelector = "last-name";
    String postalCodeNameSelector = "postal-code";
    String checkoutBtnSelector = ".checkout_button";
    String cartBtnSelector = ".cart_button";
    String completeHeaderSelector = ".complete-header";

    public void navigateToCheckoutPage(){
        WebElementActions.clickBtn(By.cssSelector(checkoutBtnSelector));
    }

    public void fillCkeckoutFrom(){
        WebElementActions.setText(By.id(firstNameSelector), "Rana");
        WebElementActions.setText(By.id(lastNameSelector), "Saad");
        WebElementActions.setText(By.id(postalCodeNameSelector), "5354");
        WebElementActions.clickBtn(By.cssSelector(cartBtnSelector));

    }
    public String finishCheckout(){
        WebElementActions.clickBtn(By.cssSelector(cartBtnSelector));
        return WebElementActions.getElement(By.cssSelector(completeHeaderSelector)).getText();
    }
}
