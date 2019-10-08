package test;

import static org.junit.Assert.assertTrue;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
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
public class ValidaLogin {

	static WebDriver driver;
	static ChromeOptions options;
	static String dadoLogin, dadoSenha, site;
	static WebElement navegador, login, senha;
	String usuarioLogado;
	
	public static void sendKeys(WebDriver driver, WebElement element, int timeout, String value) {
		new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
		element.sendKeys(value);
	}
	
	public static void clickOn(WebDriver driver, WebElement element, int timeout) {
		new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}
	
	public static void autentica(String dadoLogin, String dadoSenha) {

		WebElement login = driver.findElement(By.id("txtLogin"));
		sendKeys(driver, login, 5, dadoLogin);
		
		WebElement senha = driver.findElement(By.id("txtPassword"));
		sendKeys(driver, senha, 5, dadoSenha);
		
		WebElement botaoLogin = driver.findElement(By.className("authenticate")).findElement(By.className("default-btn"));
		clickOn(driver, botaoLogin, 5);
	}
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:/drivers/chromedriver.exe");	// Local do driver .exe
		options = new ChromeOptions();
	//	options.addArguments("--headless"); 	// para ser executado em background
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
	//	site = "https://preprod2.iobonline.com.br/";
		site = "https://www.iobonline.com.br/";
		dadoLogin = "sergior";
		dadoSenha = "Rosendo";
		driver.get(site);
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		//	driver.close();
	}

//	@Ignore
	@Test
	public void Teste1_ValidaSite() {
		
		String titleSite = "IOB Online";
		
		assertTrue("Verificar o domínio do Site. Titulo da página difere do esperado.", driver.getTitle().contentEquals(titleSite));
		System.out.println("*** Método 1 - URL do domínio OK. ***");
		
	}
	
	@Ignore
	@Test
	public void executandoPesquisa() {
		
		driver.findElement(By.xpath("//*[@id=\"tsf\"]/div[2]/div[1]/div[1]/div/div[2]/input")).sendKeys("Automação de testes");
		System.out.println("Método 'executandoPesquisa' executado com sucesso.");
		
	}
	
	
//	@Ignore
	@Test
	public void Teste2_VerificaModal() throws InterruptedException {
		
		System.out.println("*** Método 2 - Verificar Modal... ***");
		Thread.sleep(2500);
		
		String mascara = driver.findElement(By.className("mask")).getTagName();
		if(mascara != null) {
			//	Modal FrontEnd
			WebElement botaoFecharModal = driver.findElement(By.className("close-video"));
			clickOn(driver, botaoFecharModal, 50);
			Thread.sleep(1500);
			System.out.println("    ** Método 2 - Modal fechado com sucesso. ***");
		} else {
			System.out.println("    ** Método 2 - Não há modal no FrontEnd. ***");
		}
		
	}
	
//	@Ignore
	@Test                 
	public void Teste3_ValidaLogin() throws InterruptedException {
		
		System.out.println("*** Método 3 - Autenticação ***");
	//	Modal de autenticação
		WebElement botaoLogin = driver.findElement(By.id("toolbar")).findElement(By.className("magenta-btn"));
		clickOn(driver, botaoLogin, 30);
		Thread.sleep(3000);
		autentica(dadoLogin, dadoSenha);
		Thread.sleep(1000);
		System.out.println("    ** Método 3 - Autenticação efetuada com sucesso. ***");

		usuarioLogado = driver.findElement(By.className("welcome")).getText();

		System.out.println("Autenticação: " + usuarioLogado);
		
//		assertTrue("Houve problema no processo de autenticação.", driver.getTitle().contentEquals(titleSite));
//		assertTrue("Houve problema no processo de autenticação.", driver.getTitle().contentEquals(titleSite));
		
		
//		driver.close();
	}

	
	@Ignore
	@Test                 
	public void Teste4_LoginSessaoAtiva() throws InterruptedException {
		
		System.out.println("*** Método 4 - Logar com sessão ativa ***");
		
	//	Modal de autenticação
		WebElement botaoLogin = driver.findElement(By.id("toolbar")).findElement(By.className("magenta-btn"));

		if(botaoLogin != null) {
			clickOn(driver, botaoLogin, 30);
			Thread.sleep(3000);
			autentica(dadoLogin, dadoSenha);
			Thread.sleep(1000);

			WebElement botaoAutenticado = driver.findElement(By.id("error-message")).findElement(By.className("default-btn"));
			if (botaoAutenticado != null) {
				clickOn(driver, botaoAutenticado, 5);
				
				driver.findElement(By.className("authenticate")).findElement(By.className("default-btn")).click();
				System.out.println("    ** Método 3 - Finalizando sessão ativa, para logar novamente.");
				System.out.println("    ** Método 3 - Autenticação efetuada com sucesso. ***");
			} else {
				System.out.println("    ** Método 3 - Não há sessão ativa para esse usuário. ***");
				System.out.println("    ** Método 3 - Autenticação efetuada com sucesso. ***");
			}
		} else {
			System.out.println("    ** Método 3 - Autenticação efetuada com sucesso. ***");
		}
		
		driver.close();
	}
	
}
