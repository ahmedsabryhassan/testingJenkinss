package POM;

import actions.WebElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Models.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class ProductsPage {
    String productsSelector = ".inventory_item";
    String addToCartBtnSelector = ".btn_inventory";
    String removeFromCartBtnSelector ="//button[@class = 'btn_secondary btn_inventory' ]";
    String cartNumberSelector = ".shopping_cart_badge";
    String productTitleSelector = "a[id$='_title_link']";
    String productNameSelector = ".inventory_item_name";
    String productDescSelector = ".inventory_item_desc";
    String productPriceSelector = ".inventory_item_price";

    public List<WebElement> selectedProducts;

    public void addRandomProductsToCart() {
        List<WebElement> allProducts = WebElementActions.getElements(By.cssSelector(productsSelector));

        int halfCount = (int) Math.ceil(allProducts.size() / 2.0);

        Collections.shuffle(allProducts);

        selectedProducts = allProducts.subList(0, halfCount);

        for (int i = 0; i < selectedProducts.size(); i++) {
            WebElementActions.clickBtn(selectedProducts.get(i).findElement(By.cssSelector(addToCartBtnSelector)));
        }
    }

    public void removeAddedProductsInCart() {
        List<WebElement> removeBtns = WebElementActions.getElements(By.xpath(removeFromCartBtnSelector));

        if (removeBtns == null)
        {
            if (selectedProducts != null) 
                selectedProducts.clear();
            return;
        } 

        for (int i = 0; i < removeBtns.size(); i++) 
            WebElementActions.clickBtn(removeBtns.get(i));

        selectedProducts.clear();
    }

    public boolean isValidCartNumber() {
        WebElement cartNumber = WebElementActions.getElement(By.cssSelector(cartNumberSelector));

        if (cartNumber == null && selectedProducts.size() == 0)
            return true;
        else if (cartNumber != null && Integer.parseInt(cartNumber.getText().trim()) == selectedProducts.size())
            return true;
        return false;
    }

    public List<Product> getAddedProducts() {
        if (selectedProducts == null || selectedProducts.size() == 0)
            return null;

        List<Product> products = new ArrayList<>();

        for (WebElement element : selectedProducts) {
            try {
                String id = element.findElement(By.cssSelector(productTitleSelector)).getDomProperty("id").trim();
                String name = element.findElement(By.cssSelector(productNameSelector)).getText().trim();
                String desc = element.findElement(By.cssSelector(productDescSelector)).getText().trim();
                String price = element.findElement(By.cssSelector(productPriceSelector)).getText().trim();

                products.add(new Product(id, name, desc, price));
            } catch (NoSuchElementException e) {
                // Log or handle missing elements gracefully
                System.out.println("Error parsing product: " + e.getMessage());
            }
        }
        return products;
    }
}
