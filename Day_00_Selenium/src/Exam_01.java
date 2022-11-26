import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Exam_01 {
	public static void main(String[] args) throws Exception {
		// ������̹� �ν��Ͻ��� ����� ������ ����
		
		WebDriver driver = (WebDriver) new ChromeDriver();
		driver.get("http://www.naver.com");
		
		WebElement searchInput = driver.findElement(By.id("query")); //driver.findElement(null) : �Ű������� �ڷ����� By by
		searchInput.sendKeys("Hello Selenium");
		System.out.println(searchInput); // null �� �ƴ� ���� ���´ٸ� ������ ��
		
		WebElement searchBtn = driver.findElement(By.id("search_btn"));
		System.out.println(searchBtn);
		
		
		Thread.sleep(5000);
		driver.close();
	}
}
