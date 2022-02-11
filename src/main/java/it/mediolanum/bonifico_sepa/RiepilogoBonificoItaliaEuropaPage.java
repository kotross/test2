package it.mediolanum.bonifico_sepa;

import it.mediolanum.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RiepilogoBonificoItaliaEuropaPage extends BasePage {

    @FindBy(xpath = "//input[@id='mailOrdinanteCheck']/..//span")
    WebElement checkboxMailOrdinante;

    @FindBy(xpath = "//input[@id='mailBeneficiarioCheck']/..//span")
    WebElement checkboxMailBeneficiario;

    @FindBy(xpath = "//input[@id='privacy']/..//span")
    WebElement checkboxPrivacy;

    @FindBy(id = "confirm")
    WebElement btnContinua;

    public RiepilogoBonificoItaliaEuropaPage(WebDriver driver){
        super(driver);
    }

    public void waitLoadPage() {
        try{
            getWaiter(60).until(ExpectedConditions.visibilityOfAllElements(checkboxMailOrdinante, checkboxMailBeneficiario,
            checkboxPrivacy));
//            getWaiter(30).until(ExpectedConditions.elementToBeClickable(checkboxMailBeneficiario));
//            getWaiter(30).until(ExpectedConditions.elementToBeClickable(checkboxPrivacy));
//            getWaiter(30).until(ExpectedConditions.elementToBeClickable(btnContinua));
        }catch (TimeoutException e){
            throw new RuntimeException("Pagina Riepilogo  Bonifico sepa non caricata: "+ e);
        }
    }

    public void compilaCodiceDiSicurezza(String pinCliente){
        waitLoadPage();
        int posizione = 0;
        try {
            for(int i = 0; i <= 4; i++){
                posizione = Integer.parseInt(driver.findElement(By.xpath(String.format("//input[@data-position='%s']", i + 1))).getAttribute("data-position"));
                if (driver.findElement(By.xpath(String.format("//input[@data-position='%s']", i + 1))).isEnabled()) {
                    driver.findElement(By.xpath(String.format("//input[@data-position='%s']", i + 1))).sendKeys(String.valueOf(pinCliente.charAt(posizione - 1)));
                }
            }
        }catch (TimeoutException e){
            throw new RuntimeException("Codice di sicurezza non compilato: "+ e);
        }
    }

    public void clickContinua() {
        try {
            btnContinua.click();
        }catch (TimeoutException e){
            throw new RuntimeException("Bottone continua non andato a buon fine: "+ e);
        }
    }

}
