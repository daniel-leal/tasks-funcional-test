package br.ce.wcaquino.tasks.functional;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TasksTest {
	
	public WebDriver acessarAplicacao() {
		System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver_linux64/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8001/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		return driver;
	}
	
	@Test
	public void deveSalvarTarefaComSucesso() {
		WebDriver driver = acessarAplicacao();
		try {
			// Clicar em add Todo
			driver.findElement(By.id("addTodo")).click();
			
			// Preencher descricao
			driver.findElement(By.id("task")).sendKeys("Teste Via Selenium");
			
			// Preencher data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");
			
			// Clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			// Validar mensagem de sucesso!
			String mensagem = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Success!", mensagem);
		} finally {
			// Fechar o browser
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemDescricao() {
		WebDriver driver = acessarAplicacao();
		try {
			// Clicar em add Todo
			driver.findElement(By.id("addTodo")).click();
			
			// Preencher data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2020");
			
			// Clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			// Validar mensagem de sucesso!
			String mensagem = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the task description", mensagem);
		} finally {
			// Fechar o browser
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemData() {
		WebDriver driver = acessarAplicacao();
		try {
			// Clicar em add Todo
			driver.findElement(By.id("addTodo")).click();
			
			// Preencher descricao
			driver.findElement(By.id("task")).sendKeys("Teste Via Selenium");
			
			// Clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			// Validar mensagem de sucesso!
			String mensagem = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the due date", mensagem);
		} finally {
			// Fechar o browser
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaComDataPassada() {
		WebDriver driver = acessarAplicacao();
		try {
			// Clicar em add Todo
			driver.findElement(By.id("addTodo")).click();
			
			// Preencher descricao
			driver.findElement(By.id("task")).sendKeys("Teste Via Selenium");
			
			// Preencher data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2010");
			
			// Clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			// Validar mensagem de sucesso!
			String mensagem = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Due date must not be in past", mensagem);
		} finally {
			// Fechar o browser
			driver.quit();
		}
	}
}
