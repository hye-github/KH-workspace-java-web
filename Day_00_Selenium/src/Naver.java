import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Naver {
   public static void main(String[] args) throws Exception {
	   
      WebDriver driver = (WebDriver) new ChromeDriver();
      JavascriptExecutor js = (JavascriptExecutor) driver;
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
      
      try {
         driver.get("https://map.naver.com/v5/?c=14142278.5235720,4506750.4678391,15,0,0,0,dh");
         
         
         Thread.sleep(1500);
         
         Account account = new Account();
         js.executeScript("document.getElementById('id').value=arguments[0]",account.getId());
         js.executeScript("document.getElementById('pw').value=arguments[0]",account.getPw());

         // ������ ������ �� ������ ��ٸ�����, ���������δ� 0.5�� ������ �ݺ����� �����ϸ� �˻���.
         
         wait.until(ExpectedConditions.presenceOfElementLocated(By.id("log.login")));
         WebElement btnLogin = driver.findElement(By.id("log.login"));
         btnLogin.click();
         //Thread.sleep(1500);

         driver.get("https://mail.naver.com/");

         wait.until(ExpectedConditions.presenceOfElementLocated(By.className("btn_import")));
         WebElement btnWriteToSelf = driver.findElement(By.className("btn_import"));
         btnWriteToSelf.click();

         String content = "Test input text.";
         driver.switchTo().frame("se2_iframe"); //move focus in iframe
         wait.until(ExpectedConditions.presenceOfElementLocated(By.className("se2_inputarea")));
         WebElement inputArea = driver.findElement(By.className("se2_inputarea"));
         inputArea.sendKeys(content);
         driver.switchTo().parentFrame();
         
         WebElement subject = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("subject")));
         js.executeScript("document.getElementById('subject').value=arguments[0]", "Test");

         
         // driver.switchTo().defaultContent();

         wait.until(ExpectedConditions.presenceOfElementLocated(By.id("attachListBlockLayer")));
         WebElement sendBtn = driver.findElement(By.id("sendBtn"));
         //sendBtn.click();


      } catch (Exception e) {
         e.printStackTrace();
         driver.close();

      }
   }

}