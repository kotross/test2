package it.mediolanum.ricarica_telefonica;

import it.mediolanum.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RicaricaTelefonicaVodafonePage extends BasePage {

    @FindBy(xpath = "//legend[text()='Informazioni sul servizio']")
    WebElement lblLegenda;

    @FindBy(xpath = "//div[@id='Div_ricarica_TIM']//input[@id='txtPrefisso']")
    WebElement txtbPrefisso;

    @FindBy(xpath = "//div[@id='Div_ricarica_TIM']//input[@id='txtNumero']")
    WebElement txtbNumero;

    @FindBy(id = "btnSubmit_wrap")
    WebElement btnVai;

    @FindBy(xpath = "//input[@id='rad_importo' and @value='1000_00']")
    WebElement radioRicDieci;

    @FindBy(xpath = "//input[@id='rad_importo' and @value='10000_00']")
    WebElement radioRicCento;

    @FindBy(xpath = "//div[@id='button']/input[@id='Display_buttons']")
    WebElement btnContinua;

    @FindBy(id = "framePrinc")
    WebElement iFrameRic;

    public RicaricaTelefonicaVodafonePage(WebDriver driver){
        super(driver);
    }

    private void waitLoadPage(){
        try{
            getWaiter(30).until(ExpectedConditions.elementToBeClickable(txtbPrefisso));
            getWaiter(30).until(ExpectedConditions.elementToBeClickable(txtbNumero));
            getWaiter(30).until(ExpectedConditions.elementToBeClickable(btnVai));
        }catch (TimeoutException e){
            throw new RuntimeException("Pagina Ricarica telefonica VODAFONE non caricata: "+ e);
        }
    }

    private void waitLoadFrame(){
        try{
            getWaiter(30).until(ExpectedConditions.visibilityOf(iFrameRic));
        }catch (TimeoutException e){
            throw new RuntimeException("iFrame Ricarica telefonica VODAFONE non caricata: "+ e);
        }
    }

    private void waitLoadImportiRicarica() {
        try {
            getWaiter(30).until(ExpectedConditions.elementToBeClickable(radioRicDieci));
            getWaiter(30).until(ExpectedConditions.elementToBeClickable(radioRicCento));
            getWaiter(30).until(ExpectedConditions.elementToBeClickable(btnContinua));
        }catch (TimeoutException e) {
            throw new RuntimeException("Importi ricarica non caricati: " + e);
        }
    }

    public void compilaPrefisso(String prefisso){
        waitLoadFrame();
        driver.switchTo().frame(iFrameRic);
        waitLoadPage();
        txtbPrefisso.sendKeys(prefisso);
        driver.switchTo().defaultContent();
    }

    public void compilaNumero(String numero){
        driver.switchTo().frame(iFrameRic);
        waitLoadPage();
        txtbNumero.sendKeys(numero);
        driver.switchTo().defaultContent();
    }

    public void clickVai(){
        waitLoadFrame();
        driver.switchTo().frame(iFrameRic);
        waitLoadPage();
        btnVai.click();
        driver.switchTo().defaultContent();
    }

    public void selezioneImportoRicarica(String importo){
        waitLoadFrame();
        driver.switchTo().frame(iFrameRic);
        waitLoadImportiRicarica();
        switch (importo){
            case "10": driver.findElement(By.xpath("//input[@id='rad_importo' and @value='1000_00']")).click();
                break;
            case "20": driver.findElement(By.xpath("//input[@id='rad_importo' and @value='2000_00']")).click();
                break;
            case "30": driver.findElement(By.xpath("//input[@id='rad_importo' and @value='3000_00']")).click();
                break;
            case "50": driver.findElement(By.xpath("//input[@id='rad_importo' and @value='4000_00']")).click();
                break;
            case "60": driver.findElement(By.xpath("//input[@id='rad_importo' and @value='5000_00']")).click();
                break;
            case "80": driver.findElement(By.xpath("//input[@id='rad_importo' and @value='6000_00']")).click();
                break;
            case "100": driver.findElement(By.xpath("//input[@id='rad_importo' and @value='10000_00']")).click();
                break;
        }
        driver.switchTo().defaultContent();
    }

    public void selezionaContinua(){
        waitLoadFrame();
        driver.switchTo().frame(iFrameRic);
        waitLoadImportiRicarica();
        try {
            scrollTo(btnContinua);
            btnContinua.click();
        }catch (TimeoutException e){
            throw new RuntimeException("Non ho potuto premere continua ed uscire: "+ e);
        }
        driver.switchTo().defaultContent();
    }

}
