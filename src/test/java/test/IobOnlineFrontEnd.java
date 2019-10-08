package test;

import java.util.concurrent.TimeUnit;

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
public class IobOnlineFrontEnd {

	static WebDriver driver;
	static ChromeOptions options;
	static String dadoLogin, dadoSenha, site;
	static WebElement navegador, login, senha;
	String usuarioLogado;
		
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
	
	@Test
	public void Teste1_FecharModal() throws InterruptedException {
		
		System.out.println("*** Método 1 - Fechar Modal FrontEnd ***");
		String statusModal = "";
		String modal = driver.findElement(By.className("close-video")).getText();

		if (statusModal != modal) {
			WebElement botaoFecharModal = driver.findElement(By.className("close-video"));
			clickOn(driver, botaoFecharModal, 50);
			Thread.sleep(1500);
			System.out.println("*** Método 1 - Modal fechado com sucesso. ***");
		} else {
			System.out.println("*** Método 1 - Sem modal | Não exigir mais. ***");
		}
	}
	
	@Test
	public void Teste2_ValidaLogin() throws InterruptedException {
		
		System.out.println("*** Método 2 - Autenticar ***");

	//	Abrindo modal de autenticação	
		WebElement botaoLogin = driver.findElement(By.id("toolbar")).findElement(By.className("magenta-btn"));
		clickOn(driver, botaoLogin, 30);
		Thread.sleep(3000);
		System.out.println("*** Método 2 - Modal de login aberto com sucesso. ***");
		autentica(dadoLogin, dadoSenha);
		Thread.sleep(1000);
		usuarioLogado = driver.findElement(By.className("welcome")).getText();
		
	}

	@Test
	public void Teste3_ValidaLoginSessaoAberta() {
		
		driver.get(site);
		
		System.out.println("Verificando validação de usuário com sessão aberta...");
		
		autentica(dadoLogin, dadoSenha);
		WebElement botaoAutenticado = driver.findElement(By.id("error-message")).findElement(By.className("default-btn"));
		clickOn(driver, botaoAutenticado, 5);
		
		driver.findElement(By.className("authenticate")).findElement(By.className("default-btn")).click();
		System.out.println("*** Método 3 - Finalizando sessão ativa, para logar novamente.");
	}
	
	@Test
	public void Teste4_Final() {
		
		System.out.println("*** Método 4 - Teste Final ***");
	//	driver.get(site);
		String teste = "on";
		
		if (teste == "on") {
			
			/*WebElement botaoFecharModal = driver.findElement(By.className("close-video"));
			clickOn(driver, botaoFecharModal, 20);
			
			System.out.println("Modal fechado com sucesso.");
			
			WebElement botaoLogin = driver.findElement(By.id("toolbar")).findElement(By.className("magenta-btn"));
			clickOn(driver, botaoLogin, 50);
			
			System.out.println("Login chamado com sucesso.");*/
			
		} else {
		/* Verificacao de modal */
			String statusModal = "";
			String modal = driver.findElement(By.className("close-video")).getText();
			System.out.println("Texto do modal: " + modal);
			
			if (statusModal != modal) {
				driver.findElement(By.className("close-video")).click();
				driver.manage().timeouts().setScriptTimeout(10,TimeUnit.SECONDS);
				System.out.println("Modal fechado.");
			} else {
				System.out.println("Sem modal | Não exigir mais.");
			}
			
			/* Autenticacao */
			driver.manage().timeouts().setScriptTimeout(100,TimeUnit.SECONDS);
			driver.findElement(By.className("magenta-btn")).click();
			driver.manage().timeouts().setScriptTimeout(10,TimeUnit.SECONDS);
				driver.findElement(By.id("txtLogin")).sendKeys(dadoLogin);
				driver.findElement(By.id("txtPassword")).sendKeys(dadoSenha);
				driver.findElement(By.className("authenticate")).findElement(By.className("default-btn")).click();
				
				try {
					driver.manage().timeouts().setScriptTimeout(10,TimeUnit.SECONDS);
					String usuarioLogadoo = driver.findElement(By.id("error-message")).findElement(By.className("default-btn")).getText();
					driver.manage().timeouts().setScriptTimeout(10,TimeUnit.SECONDS);
					if (usuarioLogadoo != "") {
						driver.manage().timeouts().setScriptTimeout(10,TimeUnit.SECONDS);
							driver.findElement(By.id("error-message")).findElement(By.className("default-btn")).click();
						driver.manage().timeouts().setScriptTimeout(10,TimeUnit.SECONDS);
							driver.findElement(By.className("authenticate")).findElement(By.className("default-btn")).click();
						driver.manage().timeouts().setScriptTimeout(10,TimeUnit.SECONDS);
					}
				} catch (Exception e) {
					// TODO: handle exception
					driver.manage().timeouts().setScriptTimeout(10,TimeUnit.SECONDS);
						driver.findElement(By.id("error-message")).findElement(By.className("default-btn")).click();
					driver.manage().timeouts().setScriptTimeout(10,TimeUnit.SECONDS);
						driver.findElement(By.className("authenticate")).findElement(By.className("default-btn")).click();
					driver.manage().timeouts().setScriptTimeout(10,TimeUnit.SECONDS);
						System.out.println("Forçando autenticação.");
				} finally {
					System.out.println("Usuário já autenticado.");
				}

				String logadonull = "";
				String logado = driver.findElement(By.className("welcome")).getText();
				
				if(logado == logadonull) {
					System.out.println("Não está logado!!");
				} else {
					System.out.println("Logado com sucesso.");
				}
		}

		//	System.out.println(logado);

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	System.out.println("Processo finalizado!");
		//	driver.close();
	}

}
