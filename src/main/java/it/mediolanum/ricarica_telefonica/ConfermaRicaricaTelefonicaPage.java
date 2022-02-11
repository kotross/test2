package it.mediolanum.ricarica_telefonica;

import it.mediolanum.core.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ConfermaRicaricaTelefonicaPage extends BasePage {

    @FindBy(xpath = "//b[contains(text(), 'effettuata sul numero:')]")
    WebElement lblOperatore;

    @FindBy(xpath = "//b[text()='Importo della ricarica ']")
    WebElement lblImportoRicarica;

    @FindBy(xpath = "//b[text()='Costi: ']")
    WebElement lblCosti;

    @FindBy(xpath = "//b[text()='Data di accettazione ']") // //b[contains(text(), 'Data di accettazione')]
    WebElement lblDataAccettazione;

    @FindBy(xpath = "//b[text()='Data di addebito: ']")
    WebElement lblDataAddebito;

    @FindBy(xpath = "//b[text()='Data di regolamento ']")
    WebElement lblDataRegolamento;

    @FindBy(id = "framePrinc")
    WebElement iFrame;

    public ConfermaRicaricaTelefonicaPage(WebDriver drive) {
        super(drive);
    }

    public void waitLoadPage(){
        try {
            getWaiter(60).until(ExpectedConditions.visibilityOfAllElements(lblOperatore,
                    lblImportoRicarica, lblCosti, lblDataAccettazione, lblDataAddebito, lblDataRegolamento));
        }catch (TimeoutException e){
            throw new RuntimeException("Pagina dettaglio ricarica non caricata: "+ e);
        }
    }

    private void waitLoadFrame(){
        try{
            getWaiter(30).until(ExpectedConditions.visibilityOf(iFrame));
        }catch (TimeoutException e){
            throw new RuntimeException("iFrame Ricarica telefonica VODAFONE non caricata: "+ e);
        }
    }

    public String getNumeroTelefono(){
        waitLoadFrame();
        driver.switchTo().frame(iFrame);
        waitLoadPage();

        String num;
        try {
            num = driver.findElement(By.xpath("//b[contains(text(), 'effettuata sul numero:')]/following-sibling::p")).getText();
        }catch (NoSuchElementException e){
            throw new RuntimeException("Numero di telefono non trovoato: "+ e);
        }
        driver.switchTo().defaultContent();
        return num;
    }

/*
    public boolean checkNumeroTelefono(String numero){
        waitLoadFrame();
        driver.switchTo().frame(iFrame);
        waitLoadPage();
        boolean isFound;
        try {
            driver.findElement(By.xpath(String.format("//b[contains(text(), 'effettuata sul numero:')]/../p[contains(text(), '%s')]", numero)));
            isFound = true;
        } catch (NoSuchElementException e) {
            isFound = false;
        }
        driver.switchTo().defaultContent();
        return isFound;
    }
*/
    public String getImportoRicarica(){
        waitLoadFrame();
        driver.switchTo().frame(iFrame);
        waitLoadPage();
        String importo;
        try{
            importo = driver.findElement(By.xpath("//b[text()='Importo della ricarica ']/following-sibling::p")).getText();
        }catch (NoSuchElementException e){
            throw new RuntimeException("Importo non trovato: "+ e);
        }
        return importo;
    }



}
