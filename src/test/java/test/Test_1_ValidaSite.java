package test;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Test_1_ValidaSite {

	static WebDriver driver;
	static ChromeOptions options;
	static String site;
	static WebElement navegador;
	
	public static void sendKeys(WebDriver driver, WebElement element, int timeout, String value) {
		new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
		element.sendKeys(value);
	}
	
	public static void clickOn(WebDriver driver, WebElement element, int timeout) {
		new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:/drivers/chromedriver.exe");	// Local do driver .exe
		options = new ChromeOptions();
		options.addArguments("--headless"); 	// para ser executado em background
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
	//	site = "https://preprod2.iobonline.com.br/";
	//	site = "https://preprod.iobonline.com.br/pages/core/login_old.jsf";
		site = "https://www.iobonline.com.br/";
		driver.get(site);
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		driver.close();
	}

	@Test
	public void Teste1_ValidaSite() {
		
		String titleSite = "IOB Online";
		
		assertTrue("Verificar o domínio do Site. Titulo da página difere do esperado.", driver.getTitle().contentEquals(titleSite));
		System.out.println("*** Test_1_Método 1 - URL do domínio OK. ***");
		
	}
	
}
