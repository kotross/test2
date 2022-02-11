package it.mediolanum;

import it.mediolanum.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class HomePage extends BasePage {

    @FindBy(xpath = "//div[@class='collapsible_title']/h1[text()='CONTO']")
    WebElement lblBoxConto;

    @FindBy(className = "container_saldo_importo")
    WebElement lblSaldoDisponibile;

    @FindBy(xpath = "//h1[text()='IL MIO PATRIMONIO']")
    WebElement lblBoxMioPatrimonio;

    @FindBy(xpath = "//div[@class='description' and text()='Totale']")
    WebElement lblTotalePatrimonio;

    @FindBy(xpath = "//span[@class='title_desc with_icon' and text()='SEND MONEY']")
    WebElement lblBoxSendMoney;

    @FindBy(xpath = "//a[@data-id='BANNER_AMEX_SELF']/p/img")
    WebElement btnAmericanExpress;

    @FindBy(className = "area_personale")
    WebElement lblAreaPersonale;

    @FindBy(xpath = "//a[contains(text(), 'Servizi Bancari')]")
    WebElement btnMenuServiziBancari;

    @FindBy(xpath = "//div[@class='resp_bgMenu']//a[contains(text(), 'Ricarica telefonica')]/../../span")
    WebElement btnRicaricaTelefonica;

    @FindBy(xpath = "//a[contains(text(), 'Bonifico Italia e Europa (SEPA)')]")
    WebElement btnBonificoItaliaEuropaSepa;

    public HomePage(WebDriver driver){
        super(driver);
    }

    public void waitBoxConto(){
        try {
            getWaiter(30).until(ExpectedConditions.visibilityOf(lblBoxConto));
            getWaiter(30).until(ExpectedConditions.visibilityOf(lblSaldoDisponibile));
        }catch (TimeoutException e){
            throw new RuntimeException("Box Conto non caricato: "+ e);
        }
    }

    public void waitBoxMioPatrimonio(){
        try {
            getWaiter(30).until(ExpectedConditions.visibilityOf(lblBoxMioPatrimonio));
            getWaiter(30).until(ExpectedConditions.visibilityOf(lblTotalePatrimonio));
        }catch (TimeoutException e){
            throw new RuntimeException("Box Mio Patrimonio non caricato: "+ e);
        }
    }

    public void waitBoxSendMoney(){
        try {
            getWaiter(30).until(ExpectedConditions.visibilityOf(lblBoxSendMoney));
        }catch (TimeoutException e){
            throw new RuntimeException("Box Send Money non caricato: "+ e);
        }
    }

    public void waitSpalla(){
        try {
            getWaiter(30).until(ExpectedConditions.visibilityOf(btnAmericanExpress));
        }catch (TimeoutException e){
            throw new RuntimeException("Menu' laterale non caricato: "+ e);
        }
    }

    public void waitAreaPersonale(){
        try {
            getWaiter(5).until(ExpectedConditions.visibilityOf(lblAreaPersonale));
        }catch (TimeoutException e){
            throw new RuntimeException("Menu' laterale non caricato: "+ e);
        }
    }

    public void paginaCaricata(){
        try {
            waitBoxConto();
            waitBoxMioPatrimonio();
            waitBoxSendMoney();
            waitSpalla();
            waitAreaPersonale();
        }catch (TimeoutException e) {
            throw new RuntimeException("Pagina non caricata: "+ e);
        }
    }

    public void clickMenuServiziBancari(){
        paginaCaricata();
        btnMenuServiziBancari.click();
    }

    public void clickRicaricaTelefonica(String operatore){
        try {
            clickMenuServiziBancari();
            Thread.sleep(2000);
//            getWaiter(10).until(ExpectedConditions.elementToBeClickable(btnRicaricaTelefonica)).click();
            btnRicaricaTelefonica.click();
            switch (operatore){
                case "TIM": getWaiter(10).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[contains(text(), 'TIM')]")))).click();
                    break;
                case "VODAFONE": getWaiter(10).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[contains(text(), 'Vodafone')]")))).click();
                    break;
                case "WINDTRE": getWaiter(10).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[contains(text(), 'WINDTRE')]")))).click();
                    break;
            }
        }catch (TimeoutException | InterruptedException e){
            throw new RuntimeException("Sottomenu Ricarica telefonica non clickato: "+ e);
        }
    }

    public void clickBonificoItaliaEuropa() {
        try{
            clickMenuServiziBancari();
            Thread.sleep(2000);
            btnBonificoItaliaEuropaSepa.click();
        } catch (InterruptedException | TimeoutException e) {
            throw new RuntimeException("Sottomenu Bonifico Italia Europa SEPA non clickato: "+ e);
        }
    }

}
