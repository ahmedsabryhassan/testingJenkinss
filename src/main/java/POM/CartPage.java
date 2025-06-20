package POM;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Models.Product;
import actions.WebDriverActions;
import actions.WebElementActions;

public class CartPage {
    String url = "https://www.saucedemo.com/v1/cart.html";
    String cartsProductsSelector = ".cart_item_label";
    String productNameSelector = ".inventory_item_name";
    String productDescSelector = ".inventory_item_desc";
    String productPriceSelector = ".inventory_item_price";

    public void navigateToCartPage() {
        WebDriverActions.navigateToPage(url);
    }

    public boolean isValidCart(List<Product> addedProducts) {
        List<WebElement> cartProducts = WebElementActions.getElements(By.cssSelector(cartsProductsSelector));

        if ((addedProducts == null || cartProducts == null) || (addedProducts.size() != cartProducts.size()))
            return false;

        for (Product added : addedProducts) {

            if (added.id == null || added.id.isEmpty())
                return false;

            // Find matching product in cartProducts
            WebElement cartProd = cartProducts.stream()
                    .filter(cart -> {
                        WebElement link;
                        try {
                            link = cart.findElement(By.cssSelector("a"));
                        } catch (NoSuchElementException e) {
                            return false;
                        }
                        String id = link.getDomAttribute("id");
                        return added.id.equals(id);
                    })
                    .findFirst().get();

            // Normalize and compare fields
            String cartTitle = cartProd.findElement(By.cssSelector(productNameSelector)).getText().trim();

            String cartDescription = cartProd.findElement(By.cssSelector(productDescSelector)).getText().trim();

            String cartPrice = normalizePrice(cartProd.findElement(By.cssSelector(productPriceSelector)).getText());

            if (!added.title.equals(cartTitle) || !added.desc.equals(cartDescription) || !normalizePrice(added.price).equals(cartPrice))
                return false;
        }
        return true;
    }


    private String normalizePrice(String priceText) {
        if (priceText == null)
            return "";
        return priceText.replaceAll("[^\\d.]", ""); // Remove currency and whitespace
    }
}
