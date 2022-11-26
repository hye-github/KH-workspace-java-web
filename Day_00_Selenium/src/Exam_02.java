import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Exam_02 {
	public static void main(String[] args) throws Exception {

		
		WebDriver driver = (WebDriver) new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		
		// �α��� URL ����
		driver.get("https://nid.naver.com/nidlogin.login?mode=form&url=https%3A%2F%2Fwww.naver.com");
		
//		WebElement searchId = driver.findElement(By.id("id"));		
//		WebElement searchPw = driver.findElement(By.id("pw"));
		
		Account account = new Account();
		js.executeScript("document.getElementById('id').value=arguments[0]", account.getId());
		js.executeScript("document.getElementById('pw').value=arguments[0]", account.getPw());
		// arguments[0] �� JDBC�� �ƱԸ�Ʈ(?) �� ����. ����δ� ��.

		WebElement btnLogin = driver.findElement(By.id("log.login"));
		btnLogin.click();
		
		driver.get("https://mail.naver.com/");
		
		WebElement btnWriteToSelf = driver.findElement(By.className("btn_import"));
		btnWriteToSelf.click();
		
//		List<WebElement> btnWriteToSelf = driver.findElements(By.className("btn_import"));
//		System.out.println(btnWriteToSelf.size());
		
		
//		Thread.sleep(3000);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("subject"))); // ExpectedConditions. �ڿ� ������ ���� õõ�� �о �ʿ䰡 �ִ�.
		// presence : ����
		
		WebElement mailSubject = driver.findElement(By.id("subject"));
		mailSubject.sendKeys("���� �Է� �׽�Ʈ �Դϴ�.");
		// �ڹ� �ڵ带 ������ ���� �������� ����� ���� ����ȭ�����ʴ´�.
		// ������ ������ ��ٷ������ʴ´�. no such element: Unable to locate element: {"method":"css selector","selector":"#subject"}

		driver.switchTo().frame("se2_iframe");
		
		WebElement mailContents = driver.findElement(By.className("se2_inputarea"));
//		List<WebElement> mailContents = driver.findElements(By.className("se2_inputarea"));
//		System.out.println(mailContents.size());
		mailContents.sendKeys("���� �Է� �׽�Ʈ �Դϴ�.");

		driver.switchTo().defaultContent();
		
		WebElement mailSend = driver.findElement(By.id("sendBtn")); // driver.switchTo().frame("se2_iframe"); ��Ŀ�� ������ ��������. driver.switchTo().defaultContent();
		mailSend.click(); 
		
//		WebElement mailSave = driver.findElement(By.id("saveBtn")); 
//		mailSave.click();
		
		
		
//		���̹��� sendkey �� ���� �Է��� Ÿ���� �ӵ� �������� �κ��� ������ ���ɼ��� ����.
//		searchId.sendKeys(Account.id);
//		searchId.sendKeys(Account.pw);
		
//		js.executeScript("document.getElementById('id').value=arguments[0]", Account.Id);
//		js.executeScript("document.getElementById('pw').value=arguments[0]", Account.Pw);
	
		
		
//		System.out.println(searchId);
//		System.out.println(searchPw);
//
//		Thread.sleep(200000);
//		driver.close();
	}
}
