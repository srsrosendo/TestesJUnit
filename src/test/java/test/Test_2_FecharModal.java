package test;

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
public class Test_2_FecharModal {

	static WebDriver driver;
	static ChromeOptions options;
	static String site;
	static WebElement navegador;
	
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

//	@Ignore
	@Test
	public void Teste2_VerificaModal() throws InterruptedException {
		
		System.out.println("*** Test_2_Método 2 - Verificar Modal... ***");
		Thread.sleep(2500);
		
		String mascara = driver.findElement(By.className("mask")).getTagName();
		if(mascara != null) {
			//	Modal FrontEnd
			WebElement botaoFecharModal = driver.findElement(By.className("close-video"));
			clickOn(driver, botaoFecharModal, 50);
			Thread.sleep(1500);
			System.out.println("    ** Test_2_Método 2 - Modal fechado com sucesso. ***");
		} else {
			System.out.println("    ** Test_2_Método 2 - Não há modal no FrontEnd. ***");
		}
		
	}
	
}
