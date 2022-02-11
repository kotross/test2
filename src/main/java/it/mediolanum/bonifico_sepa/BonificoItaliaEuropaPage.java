package it.mediolanum.bonifico_sepa;

import it.mediolanum.core.BasePage;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BonificoItaliaEuropaPage extends BasePage {

    @FindBy(id = "nomeBeneficiario")
    WebElement txtbBeneficiario;

    @FindBy(id = "iban")
    WebElement txtbIban;

    @FindBy(id = "importoPagamento")
    WebElement txtbImportoPagamento;

    @FindBy(id = "causale")
    WebElement txtbCausale;

    @FindBy(xpath = "//input[@class='ui-widget-content ui-autocomplete-input']")
    WebElement txtbTag;

    @FindBy(id = "testoNota")
    WebElement txtbNota;

    @FindBy(id = "next-step")
    WebElement btnContinua;

    public BonificoItaliaEuropaPage(WebDriver driver) {
        super(driver);
    }

    public void waitLoadPage() {
        try{
//            getWaiter(60).until(ExpectedConditions.visibilityOfAllElements(txtbBeneficiario, txtbIban, txtbImportoPagamento,
//                txtbCausale, txtbTag, txtbNota));
            getWaiter(30).until(ExpectedConditions.elementToBeClickable(txtbBeneficiario));
            getWaiter(30).until(ExpectedConditions.elementToBeClickable(txtbIban));
            getWaiter(30).until(ExpectedConditions.elementToBeClickable(txtbImportoPagamento));
            getWaiter(30).until(ExpectedConditions.elementToBeClickable(txtbCausale));
            getWaiter(30).until(ExpectedConditions.elementToBeClickable(txtbTag));
            getWaiter(30).until(ExpectedConditions.elementToBeClickable(txtbNota));
            getWaiter(30).until(ExpectedConditions.elementToBeClickable(btnContinua));
        }catch (TimeoutException e){
            throw new RuntimeException("Pagina Bonifico sepa non caricata: "+ e);
        }
    }

    public void compilaBeneficiario(String beneficiario) {
        waitLoadPage();
        try{
            txtbBeneficiario.sendKeys(beneficiario);
        }catch (TimeoutException e){
            throw new RuntimeException("Campo Beneficiario non compilato: "+ e);
        }
    }

    public void compilaIban(String iban) {
        waitLoadPage();
        try{
            txtbIban.sendKeys(iban);
        }catch (TimeoutException e){
            throw new RuntimeException("Campo Iban non compilato: "+ e);
        }
    }

    public void compilaImportoPagamento(String importo) {
        waitLoadPage();
        try{
            txtbImportoPagamento.sendKeys(importo);
        }catch (TimeoutException e){
            throw new RuntimeException("Importo pagamento non compilato: "+ e);
        }
    }

    public void clickContinua() {
        waitLoadPage();
        try{
            btnContinua.click();
        }catch (TimeoutException e){
            throw new RuntimeException("Bottone continua non clickato: "+ e);
        }
    }

}
