package test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Test_0_Exemplos {

	private String stringLocal;
	
	@BeforeClass
	public static void antesDeTudo() {
		System.out.println("Executado antes.");
	}
	
	@Before
	public void inicializaLocal() {
		
		System.out.println("Inicializou.");
		this.stringLocal = "inicializado";
		
	}
	
	@Test
	public void inicializaLocal1() {
		
		Assert.assertEquals("A string deveria estara inicializada1.", "inicializado", this.stringLocal);

		System.out.println("Método_1 efetuado com sucesso.");
		
	}
	
	@Test
	public void inicializaLocal2() {
		
		Assert.assertEquals("A String deveria ser inicializada2.", "inicializado", this.stringLocal);
		System.out.println("Método_2 efetuado com sucesso.");
	}
	
	@Test
	public void incializaLocal3() {
		
		int valor = 1;
		Assert.assertSame("A divisão de 2 por 2 deveria ser 1.", 1, valor);
		System.out.println("Método_3 efetuado com sucesso.");
		
	}
	
	@AfterClass
	public static void depoisDeTudo() {
		
		System.out.println("Executado depois.");
	}
	
	@After
	public void setaNull() {
		
		System.out.println("Setou nullo.");
		this.stringLocal = null;
		
	}
	
}
