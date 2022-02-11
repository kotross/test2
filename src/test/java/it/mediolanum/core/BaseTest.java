package it.mediolanum.core;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class BaseTest {

    protected WebDriver driver;

    @BeforeEach
    public void initTest() {
        System.out.println("Inizializzo il test");

        System.setProperty("webdriver.chrome.driver",
                "c://Users//ddemarco//Documents//SeleniumLocale//BrowserDriver//chromedriver_win32//chromedriver.exe");

        driver = new ChromeDriver();

        driver.get("https://www-test.bmedonline.it/");
        driver.manage().window().maximize();
    }

    @AfterEach
    public void endTest() {
        System.out.println("Chiudo il test");

        byte[] errorImage = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        File outputFile = new File("screenshot.png");
        try (FileOutputStream outputStream = new FileOutputStream(outputFile)) {
            outputStream.write(errorImage);
        } catch (IOException e) {
            System.out.println("Errore salvataggio screenshot");
            e.printStackTrace();
        }

        driver.manage().deleteAllCookies();
        driver.quit();
    }

}
