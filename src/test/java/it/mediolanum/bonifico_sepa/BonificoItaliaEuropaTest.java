package it.mediolanum.bonifico_sepa;

import it.mediolanum.HomePage;
import it.mediolanum.LoginPage;
import it.mediolanum.bonifico_sepa.BonificoItaliaEuropaPage;
import it.mediolanum.bonifico_sepa.RiepilogoBonificoItaliaEuropaPage;
import it.mediolanum.core.BaseTest;
import org.junit.jupiter.api.Test;

public class BonificoItaliaEuropaTest extends BaseTest {

    private static String codiceCliente = "00008573557";
    private static String pin1Cliente = "40364";
    private static String pin2Cliente = "83744";

    @Test
    public void bonificoItaliaTest(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.compilaCodiceCliente(codiceCliente);
        loginPage.compilaPinCliente(pin1Cliente);
        loginPage.clickContinua();

        HomePage homePage = new HomePage(driver);
        homePage.clickBonificoItaliaEuropa();

        BonificoItaliaEuropaPage bonificoItaliaEuropaPage = new BonificoItaliaEuropaPage(driver);
        bonificoItaliaEuropaPage.compilaBeneficiario("Davide De Marco");
        bonificoItaliaEuropaPage.compilaIban("IT72G7685529264U1L456S8D265");
        bonificoItaliaEuropaPage.compilaImportoPagamento("10");
        bonificoItaliaEuropaPage.clickContinua();

        RiepilogoBonificoItaliaEuropaPage riepilogoBonificoItaliaEuropaPage = new RiepilogoBonificoItaliaEuropaPage(driver);
        riepilogoBonificoItaliaEuropaPage.compilaCodiceDiSicurezza(pin2Cliente);
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            throw new RuntimeException("Test interrotto in attesa dell'inserimento Codice B.Med: "+ e);
        }
        riepilogoBonificoItaliaEuropaPage.clickContinua();
    }

}
