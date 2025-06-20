import POM.CartPage;
import POM.CheckoutPage;
import POM.LoginPage;

import POM.ProductsPage;
import actions.WebDriverActions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class E2ETest {
    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;

    @BeforeTest
    public void setup() {
        WebDriverActions.initializeWebDriver();
        loginPage = new LoginPage(); 
        productsPage = new ProductsPage();
        cartPage = new CartPage();
        checkoutPage = new CheckoutPage();
    }

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return new Object[][] {
                // username, password, isSuccessLogin
                {"standard_user", "secret_sauce", true},
                {"locked_out_user", "secret_sauce", false},
                {"performance_glitch_user", "secret_sauce", true},
                {"problem_user", "secret_sauce", true},
                {"incorrectusername", "pass", false},
        };
    }

    @Test(dataProvider = "loginData")
    public void e2eTest(String username, String password, boolean isSuccessLogin){
        loginPage.navigateToLoginPage(); // 3
        loginPage.login(username, password);
        var isLoginSuccess = loginPage.isLoggedUserSuccessful();
        System.out.println("Username: " + username + " Is Success Login: " + isLoginSuccess);
        Assert.assertEquals(isLoginSuccess, isSuccessLogin, "The user did not logged successfully");

        if (!isLoginSuccess)
            Assert.fail(loginPage.getLoginErrorMessage());

        //  --- Product Page ---
        // make sure that the cart always empty before adding
        productsPage.removeAddedProductsInCart();

        productsPage.addRandomProductsToCart();
        Assert.assertTrue(productsPage.isValidCartNumber(), "The Cart Number after adding proudcts not equals the added product number");
        productsPage.removeAddedProductsInCart();
        Assert.assertTrue(productsPage.isValidCartNumber(), "The Cart Number after adding proudcts not equals the added product number");

        productsPage.addRandomProductsToCart();

        //  --- Cart Page ---
        var addedProducts = productsPage.getAddedProducts();

        cartPage.navigateToCartPage();
        Assert.assertTrue(cartPage.isValidCart(addedProducts), "The Cart does not match the added products from product page");

        //  --- Checkout Page ---
        checkoutPage.navigateToCheckoutPage();
        checkoutPage.fillCkeckoutFrom();
        String checkoutCompletion = checkoutPage.finishCheckout();

        Assert.assertEquals(checkoutCompletion, "THANK YOU FOR YOUR ORDER", "The Cart does not match the added products from product page");
    }
}
