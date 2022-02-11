package it.mediolanum.ricarica_telefonica;

import it.mediolanum.HomePage;
import it.mediolanum.LoginPage;
import it.mediolanum.core.BaseTest;
import it.mediolanum.ricarica_telefonica.ConfermaRicaricaTelefonicaPage;
import it.mediolanum.ricarica_telefonica.RicaricaTelefonicaVodafonePage;
import it.mediolanum.ricarica_telefonica.RiepilogoRicaricaTelefonicaPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RicaricaTelefonicaTest extends BaseTest {

    private static String codiceCliente = "00008573557";
    private static String pin1Cliente = "40364";
    private static String pin2Cliente = "83744";

    @Test
    public void ricaricaCellulareVodafone() {
        try {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.compilaCodiceCliente(codiceCliente);
            loginPage.compilaPinCliente(pin1Cliente);
            loginPage.clickContinua();

            HomePage homePage = new HomePage(driver);
            homePage.clickRicaricaTelefonica("VODAFONE"); //operatore scritto tutto in maiuscolo

            RicaricaTelefonicaVodafonePage ricaricaTelefonicaVodafonePage = new RicaricaTelefonicaVodafonePage(driver);
            ricaricaTelefonicaVodafonePage.compilaPrefisso("335");
            ricaricaTelefonicaVodafonePage.compilaNumero("5338015");
            ricaricaTelefonicaVodafonePage.clickVai();
            ricaricaTelefonicaVodafonePage.selezioneImportoRicarica("10");
            ricaricaTelefonicaVodafonePage.selezionaContinua();

            RiepilogoRicaricaTelefonicaPage riepilogoRicaricaTelefonicaPage = new RiepilogoRicaricaTelefonicaPage(driver);
            riepilogoRicaricaTelefonicaPage.compilaCodiceDiSicurezza(pin2Cliente);
            Thread.sleep(15000);
            riepilogoRicaricaTelefonicaPage.clickContinua();

            ConfermaRicaricaTelefonicaPage confermaRicaricaTelefonicaPage = new ConfermaRicaricaTelefonicaPage(driver);
            Assertions.assertEquals("335/5338015", confermaRicaricaTelefonicaPage.getNumeroTelefono(), "Controllo numero di telefono errato");
            Assertions.assertEquals("10,00", confermaRicaricaTelefonicaPage.getImportoRicarica(), "Controllo importo ricarica errato");
        }catch (InterruptedException e){
            throw new RuntimeException("Test interrotto: "+ e);
        }
    }

}
