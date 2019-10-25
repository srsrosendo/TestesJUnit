package test;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Test_2_FecharModalFrontEnd {

	static WebDriver driver;
	static ChromeOptions options;
	static String site;
	static WebElement navegador;
	static String ErrorTeste = "SiteTeste";
	
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
	//	site = "https://www.iob.com.br/";
		driver.get(site);
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		driver.close();
	}

//	@Ignore
	@Test
	public void Teste2_VerificaModal() throws InterruptedException {
		
		System.out.println("*** Test_2_Método 1 - Verificar Modal... ***");
		
		try {
			WebElement botaoFecharModal = driver.findElement(By.className("close-video"));
			clickOn(driver, botaoFecharModal, 50);
			System.out.println("    ** Test_2_Método 1 - Modal fechado com sucesso. ***");
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("    ** Test_2_Método 1 - Não há modal no FrontEnd. ***");
			assertTrue("    ** Test_2_Método 1 - Não há modal no FrontEnd. ***", driver.getTitle().contentEquals(ErrorTeste));
		}
		
	}
	
}
