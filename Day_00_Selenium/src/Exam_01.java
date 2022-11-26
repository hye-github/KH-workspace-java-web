import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Exam_01 {
	public static void main(String[] args) throws Exception {
		// 웹드라이버 인스턴스를 만드는 것으로 시작
		
		WebDriver driver = (WebDriver) new ChromeDriver();
		driver.get("http://www.naver.com");
		
		WebElement searchInput = driver.findElement(By.id("query")); //driver.findElement(null) : 매개변수의 자료형이 By by
		searchInput.sendKeys("Hello Selenium");
		System.out.println(searchInput); // null 이 아닌 값이 나온다면 성공한 것
		
		WebElement searchBtn = driver.findElement(By.id("search_btn"));
		System.out.println(searchBtn);
		
		
		Thread.sleep(5000);
		driver.close();
	}
}
