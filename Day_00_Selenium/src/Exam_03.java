import java.time.Duration;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Exam_03 {
	public static void main(String[] args) throws Exception {

		System.out.println("<< 당일 베스트 셀러 장바구니에 담기 >>");
		System.out.print("책 순위 입력(15위까지만 가능) >> ");
		Scanner sc = new Scanner(System.in);
		int bookNum = Integer.parseInt(sc.nextLine());
		
		//
		
		WebDriver driver = (WebDriver) new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		
		JavascriptExecutor js = (JavascriptExecutor) driver;

		Account account = new Account();

		driver.get("https://www.interpark.com/member/login.do?_method=initial&GNBLogin=Y&&wid1=wgnb&wid2=wel_login&wid3=login&imfsUserPath=http%3A%2F%2Fwww.interpark.com%2Fmalls%2Findex.html%3Futm_medium%3Dcpc%26utm_source%3Dgoogle%26utm_campaign%3Dshop%255Fbrand%255F20210617%255Fcpc%255Fpaidsearch%255Fpc%26utm_content%3Dconsider%255F34%26utm_term%3D%25EC%259D%25B8%25ED%2584%25B0%25ED%258C%258C%25ED%2581%25AC%26gclid%3DEAIaIQobChMIpqfPofHK-gIVEcSWCh3twwFNEAAYASAAEgIR8vD_BwE&historyGoCnt=-1");		
		driver.switchTo().frame("loginIframe");
		js.executeScript("document.getElementById('userId').value=arguments[0]", account.getId());
		js.executeScript("document.getElementById('userPwd').value=arguments[0]", account.getPw());
		driver.findElement(By.id("btn_login")).click();
		driver.get("http://book.interpark.com/display/collectlist.do?_method=bestsellerHourNew&bookblockname=b_gnb&booklinkname=%BA%A3%BD%BA%C6%AE%C1%B8&bid1=bgnb_mn&bid2=LiveRanking&bid3=main&bid4=001");
		List<WebElement> booklist = driver.findElements(By.className("itemName"));		

		booklist.get(bookNum-1).click();
		
		
		String parentHandle = driver.getWindowHandle();

		//현재 Window 목록 조회
		Set<String> windowList = driver.getWindowHandles();
		for (String windowHandle : windowList) {
			if (parentHandle.equals(windowHandle)) {
				continue;
			}
			//원하는 Window로 이동
			driver.switchTo().window(windowHandle);
			driver.findElement(By.linkText("북카트 담기")).click();
			wait.until(ExpectedConditions.alertIsPresent());
			driver.switchTo().alert().accept();
			driver.close();
		}

		//기존 Window로 이동
		driver.switchTo().window(parentHandle);
		driver.close();
	}
}
