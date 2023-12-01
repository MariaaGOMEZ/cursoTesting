package com.cursotesting;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ApplicationTest {

    @Test
    void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    /**
     * 
     */
    @Test
    public void testIntegrador() {
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();

        driver.get("https://magento.softwaretestingboard.com/");
        String altTextoBuscado = "Radiant Tee";
        WebElement imagen = driver.findElement(By.cssSelector("img[alt='" + altTextoBuscado + "']"));
        imagen.click();

        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement talle = wait
                .until(ExpectedConditions.presenceOfElementLocated(By.id("option-label-size-143-item-169")));

        talle.click();

        WebElement color = driver.findElement(By.cssSelector(".swatch-option.color[aria-label='Blue']"));
        color.click();

        WebElement cantidadInput = driver.findElement(By.cssSelector("input.input-text.qty"));
        cantidadInput.clear();
        cantidadInput.sendKeys("2");

        WebElement addToCartButton = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[title='Add to Cart']")));

        addToCartButton.click();

        WebElement shoppingCartLink = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.linkText("shopping cart")));

        shoppingCartLink.click();

        WebElement proceedToCheckoutButton = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("button[title='Proceed to Checkout'] span")));

        proceedToCheckoutButton.click();

        // Carga del formulario
        WebElement emailInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("customer-email")));

        emailInput.click();

        emailInput.sendKeys("test@mail.com");

        WebElement lastnameInput = wait
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[name='lastname']")));

        lastnameInput.sendKeys("Gomez");

        WebElement firstnameInput = wait
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[name='firstname']")));

        firstnameInput.sendKeys("Maria");

        WebElement streetInput = wait
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='street[0]']")));

        streetInput.sendKeys("Balcarce");

        WebElement cityInput = wait
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[name='city']")));

        cityInput.sendKeys("Concordia");

        WebElement codeInput = wait
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[name='postcode']")));

        codeInput.sendKeys("90210");

        WebElement teleInput = wait
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[name='telephone']")));

        teleInput.sendKeys("3454454550");

        WebElement regionSelect = wait
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("select[name='region_id']")));

        Select select = new Select(regionSelect);

        select.selectByVisibleText("California");

        // seleccionar opcion de envio
        WebElement radioElement = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='radio'][name='ko_unique_1']")));
        radioElement.click();

        WebElement continueButton = wait
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.action.continue.primary")));

        continueButton.click();

        WebElement submitButton = wait
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[type='submit']")));
        submitButton = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        submitButton.click();

        // VALIDACIONES
        WebElement pageTitle = driver.findElement(By.cssSelector("h1.page-title"));
        WebElement spanElement = pageTitle.findElement(By.cssSelector("span"));
        String spanText = spanElement.getText();
        assertTrue(spanText.equals("Thank you for your purchase!"));

        WebElement visibleButton = driver.findElement(By.cssSelector("a.action.primary.continue"));
        assertTrue(visibleButton.isEnabled());

        WebElement createAccountLink = driver.findElement(By.xpath("//a[text()='Create an Account']"));
        assertTrue(createAccountLink.isDisplayed());

        WebElement orderParagraph = driver.findElement(By.xpath("//p[contains(text(), 'Your order # is:')]"));
        WebElement spanElement2 = orderParagraph.findElement(By.cssSelector("span"));

        String spanText2 = spanElement2.getText();
        try {
            Integer.parseInt(spanText2);
            assertTrue(true);
        } catch (NumberFormatException e) {
            assertFalse(true);
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.quit();
    }
}
