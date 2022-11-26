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
		
		
		// 로그인 URL 접속
		driver.get("https://nid.naver.com/nidlogin.login?mode=form&url=https%3A%2F%2Fwww.naver.com");
		
//		WebElement searchId = driver.findElement(By.id("id"));		
//		WebElement searchPw = driver.findElement(By.id("pw"));
		
		Account account = new Account();
		js.executeScript("document.getElementById('id').value=arguments[0]", account.getId());
		js.executeScript("document.getElementById('pw').value=arguments[0]", account.getPw());
		// arguments[0] 는 JDBC의 아규먼트(?) 와 같다. 비워두는 곳.

		WebElement btnLogin = driver.findElement(By.id("log.login"));
		btnLogin.click();
		
		driver.get("https://mail.naver.com/");
		
		WebElement btnWriteToSelf = driver.findElement(By.className("btn_import"));
		btnWriteToSelf.click();
		
//		List<WebElement> btnWriteToSelf = driver.findElements(By.className("btn_import"));
//		System.out.println(btnWriteToSelf.size());
		
		
//		Thread.sleep(3000);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("subject"))); // ExpectedConditions. 뒤에 나오는 내용 천천히 읽어볼 필요가 있다.
		// presence : 존재
		
		WebElement mailSubject = driver.findElement(By.id("subject"));
		mailSubject.sendKeys("제목 입력 테스트 입니다.");
		// 자바 코드를 실행할 때와 브라우저가 실행될 때가 동기화되지않는다.
		// 브라우저 실행을 기다려주지않는다. no such element: Unable to locate element: {"method":"css selector","selector":"#subject"}

		driver.switchTo().frame("se2_iframe");
		
		WebElement mailContents = driver.findElement(By.className("se2_inputarea"));
//		List<WebElement> mailContents = driver.findElements(By.className("se2_inputarea"));
//		System.out.println(mailContents.size());
		mailContents.sendKeys("내용 입력 테스트 입니다.");

		driver.switchTo().defaultContent();
		
		WebElement mailSend = driver.findElement(By.id("sendBtn")); // driver.switchTo().frame("se2_iframe"); 포커스 순번에 맞지않음. driver.switchTo().defaultContent();
		mailSend.click(); 
		
//		WebElement mailSave = driver.findElement(By.id("saveBtn")); 
//		mailSave.click();
		
		
		
//		네이버는 sendkey 를 통한 입력을 타이핑 속도 측정으로 로봇을 검출할 가능성이 있음.
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
