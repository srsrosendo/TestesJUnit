package test;

import static org.junit.Assert.*;

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
public class Test_4_ValidaTopo {

	static WebDriver driver;
	static ChromeOptions options;
	static String dadoLogin, dadoSenha, site;
	static WebElement navegador, login, senha;
	static String usuarioLogado;
	static String ErrorTeste = "SiteTeste";
	
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
		
		WebElement botaoLogin = driver.findElement(By.className("authenticate")).findElement(By.className("send-login"));
		clickOn(driver, botaoLogin, 5);
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
		dadoLogin = "sergior"; dadoSenha = "Rosendo";
		driver.get(site);
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	//	driver.close();
	}

	@Ignore
	@Test
	public void Teste1_ValidaSite() {
		
		String titleSite = "IOB Online";
		
		assertTrue("Verificar o domínio do Site. Titulo da página difere do esperado.", driver.getTitle().contentEquals(titleSite));
		System.out.println("*** Test_3_Método 1 - URL do domínio OK. ***");
		
	}
	
	@Ignore
	@Test
	public void executandoPesquisa() {
		
		driver.findElement(By.xpath("//*[@id=\"tsf\"]/div[2]/div[1]/div[1]/div/div[2]/input")).sendKeys("Automação de testes");
		System.out.println("Método 'executandoPesquisa' executado com sucesso.");
		
	}
	
	@Ignore
	@Test
	public void Teste2_VerificaModal() throws InterruptedException {
		
		System.out.println("*** Test_3_Método 2 - Verificar Modal... ***");
		Thread.sleep(2500);
		
		try {
			WebElement botaoFecharModal = driver.findElement(By.className("close-video"));
			clickOn(driver, botaoFecharModal, 50);
			Thread.sleep(2500);
			System.out.println("    ** Test_3_Método 2 - Modal fechado com sucesso. ***");
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("    ** Test_3_Método 2 - Não há modal no FrontEnd. ***");
			assertTrue("    ** Test_3_Método 2 - Não há modal no FrontEnd. ***", driver.getTitle().contentEquals(ErrorTeste));
		}
		
	}
	
//	@Ignore
	@Test
	public void Teste3_LoginSessaoAtiva() throws InterruptedException {

		Teste2_VerificaModal();
		
		System.out.println("*** Test_3_Método 3 - Logar com sessão ativa ***");

		try {
			//	Modal de autenticação
		//	Thread.sleep(2000);
			WebElement botaoLogin = driver.findElement(By.id("toolbar")).findElement(By.className("magenta-btn"));
		//	WebElement botaoLogin = driver.findElement(By.className("magenta-btn"));
			clickOn(driver, botaoLogin, 30);
			Thread.sleep(3000);
			autentica(dadoLogin, dadoSenha);
			Thread.sleep(1000);
			usuarioLogado = driver.findElement(By.className("welcome")).getText();
			System.out.println("    ** Test_3_Método 3 - Não há sessão ativa, usuário autenticado com sucesso!");
		} catch (Exception e) {
			// TODO: handle exception
			Thread.sleep(1500);
			WebElement botaoContinuar = driver.findElement(By.id("error-message")).findElement(By.className("default-btn"));
			clickOn(driver, botaoContinuar, 5);
			
			WebElement botaoEntrar = driver.findElement(By.className("authenticate")).findElement(By.className("default-btn"));
			clickOn(driver, botaoEntrar, 5);
			
			System.out.println("    ** Test_3_Método 3 - Finalizando sessão ativa, para logar novamente.");
			System.out.println("    ** Test_3_Método 3 - Autenticação efetuada com sucesso. ***");
		} finally {
		//	Thread.sleep(2000);
		//	WebElement botaoSair = driver.findElement(By.linkText("Sair"));
		//	clickOn(driver, botaoSair, 30);
		//	Thread.sleep(1000);
		//	driver.close();
		}
	}
	
}
