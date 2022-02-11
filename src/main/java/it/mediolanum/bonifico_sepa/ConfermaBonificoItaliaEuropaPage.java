package it.mediolanum.bonifico_sepa;

import it.mediolanum.core.BasePage;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ConfermaBonificoItaliaEuropaPage extends BasePage {

    public ConfermaBonificoItaliaEuropaPage(WebDriver driver) {
        super(driver);
    }

    public void waitLoadPage() {
        try{
            getWaiter(60).until(ExpectedConditions.visibilityOfAllElements());
        }catch (TimeoutException e){
            throw new RuntimeException("Pagina Riepilogo  Bonifico sepa non caricata: "+ e);
        }
    }



}
