package it.mediolanum;

import it.mediolanum.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//p[text()='Inserisci il primo codice']")
    protected WebElement lblBoxPin;

    @FindBy(id = "codcliente")
    protected WebElement txtCodCliente;

    @FindBy(xpath = "//button[text()='CONTINUA']")
    protected WebElement btnContinua;

    public LoginPage(WebDriver driver){
        super(driver);
    }

    public void waitLoadPage(){
        try {
            getWaiter(10).until(ExpectedConditions.visibilityOf(txtCodCliente));
            getWaiter(5).until(ExpectedConditions.visibilityOf(lblBoxPin));
        }catch (TimeoutException e){
            throw new RuntimeException("Pagina login non caricata: "+ e);
        }
    }

    public void compilaCodiceCliente(String codiceCliente){
        waitLoadPage();
        txtCodCliente.sendKeys(codiceCliente);
    }

    public void compilaPinCliente(String pin){
        waitLoadPage();
        for(int i = 0; i<=pin.length()-1; i++){
            driver.findElement(By.xpath(String.format("//button[@cifra='%s']", pin.charAt(i)))).click();
        }
    }

    public void clickContinua(){
        try {
            getWaiter(5).until(ExpectedConditions.visibilityOf(btnContinua)).click();
        }catch (TimeoutException e){
            throw new RuntimeException("Bottone Continua non clickato: " + e);
        }
    }

}
