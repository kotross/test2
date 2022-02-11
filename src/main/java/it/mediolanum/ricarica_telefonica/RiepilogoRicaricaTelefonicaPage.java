package it.mediolanum.ricarica_telefonica;

import it.mediolanum.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RiepilogoRicaricaTelefonicaPage extends BasePage {

    @FindBy(xpath = "//b[contains(text(), 'effettuata sul numero:')]")
    WebElement lblOperatore;

    @FindBy(xpath = "//b[text()='Importo della ricarica ']")
    WebElement lblImportoRicarica;

    @FindBy(xpath = "//b[text()='Costi: ']")
    WebElement lblCosti;

    @FindBy(xpath = "//b[text()='Data accettazione ']")
    WebElement lblDataAccettazione;

    @FindBy(xpath = "//b[text()='Data di addebito: ']")
    WebElement lblDataAddebito;

    @FindBy(xpath = "//b[text()='Data di regolamento ']")
    WebElement lblDataRegolamento;

    @FindBy(id = "framePrinc")
    WebElement iFrame;

    @FindBy(id = "btnSubmit")
    WebElement btnContinua;

    public RiepilogoRicaricaTelefonicaPage(WebDriver driver) {
        super(driver);
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

    public void compilaCodiceDiSicurezza(String pinCliente){
        waitLoadFrame();
        driver.switchTo().frame(iFrame);
        waitLoadPage();
        int posizione = 0;
        try {
            for(int i = 0; i <= 4; i++){
                posizione = Integer.parseInt(driver.findElement(By.xpath(String.format("//input[@position='%s']", i + 1))).getAttribute("position"));
                if (driver.findElement(By.xpath(String.format("//input[@position='%s']", i + 1))).isEnabled()) {
                    driver.findElement(By.xpath(String.format("//input[@position='%s']", i + 1))).sendKeys(String.valueOf(pinCliente.charAt(posizione - 1)));
                }
            }
        }catch (TimeoutException e){
            throw new RuntimeException("Codice di sicurezza non compilato: "+ e);
        }
        driver.switchTo().defaultContent();
    }

    public void clickContinua(){
        waitLoadFrame();
        driver.switchTo().frame(iFrame);
        waitLoadPage();
        try {
            btnContinua.click();
        }catch (TimeoutException e) {
            throw new RuntimeException("Bottone continua non clickato: "+ e);
        }
        driver.switchTo().defaultContent();
    }

}
