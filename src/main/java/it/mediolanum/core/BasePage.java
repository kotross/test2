package it.mediolanum.core;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver drive){
        this.driver = drive;
        PageFactory.initElements(driver, this);
    }

    public WebDriverWait getWaiter(int secondi) {
        return new WebDriverWait(driver, secondi);
    }

    public void scrollTo(WebElement element) throws NoSuchElementException {
        ((JavascriptExecutor) this.driver).executeScript("arguments[0].scrollIntoView(true);",
                element);
    }

}
