
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Exam_00 {
   public static void main(String[] args) throws Exception {
      WebDriver driver = (WebDriver) new ChromeDriver();
      JavascriptExecutor js = (JavascriptExecutor) driver;
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
      try {
         driver.get("https://nid.naver.com/nidlogin.login?mode=form&url=https%3A%2F%2Fwww.naver.com/");
         
         
         Thread.sleep(1500);

         
         
         try {Thread.sleep(1000);} catch (InterruptedException e) {}
 		
 		//class="nav" 인 모든 태그를 가진 WebElement리스트를 받아온다.
 		//WebElement는 html의 태그를 가지는 클래스이다.
 		List<WebElement> el1 = driver.findElements(By.className("nav"));
 		
 		for (int i = 0; i < el1.size(); i++) {
 			//nav 클래스의 객체 중 "뉴스"라는 텍스트를 가진 WebElement를 클릭한다.
 			if(el1.get(i).getText().equals("뉴스")) {
 				el1.get(i).click();
 				break;
 			}
 		}
 		
 		//1초 대기
 		try {Thread.sleep(1000);} catch (InterruptedException e) {}

 		//버튼을 클릭했기 때문에 브라우저는 뉴스창으로 이동돼 있다.
 		//이동한 뉴스 창의 IT/과학 뉴스 헤드라인을 가져온다.
 		
 		//iT/과학뉴스를 담은 div
 		WebElement el2 = driver.findElement(By.id("section_it"));
 		
 		//div속에서 strong태그를 가진 모든 element를 받아온다.
 		List<WebElement> el3 = el2.findElements(By.tagName("strong"));
 		
 		int count = 0;
 		for (int i = 0; i < el3.size(); i++) {
 			//뉴스의 제목을 모두 출력한다.
 			System.out.println(++count + "번 뉴스: "+ el3.get(i).getText());
 		}
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         WebElement item = driver.findElement(By.className("item"));

         var stTime = new Date().getTime(); 
         while (new Date().getTime() < stTime + 30000) { 
             Thread.sleep(500); 
             ((JavascriptExecutor)driver).executeScript("window.scrollTo(0, document.body.scrollHeight)", item);
         }

         //item에 대한 리스트 가져오기
         List<WebElement> elements = driver.findElements(By.className("item"));

         //item 요소 조회, item은 객체이기 때문에 for문을 사용해서 조회하기
         for (WebElement element : elements) {
             driver.manage().timeouts().implicitlyWait(100000, TimeUnit.MILLISECONDS);
             driver1.manage().timeouts().implicitlyWait(100000, TimeUnit.MILLISECONDS);

             String url = element.findElement(By.tagName("a")).getAttribute("href");
             driver1.navigate().to(url); //url로 이동
             Thread.sleep(500);

             //content 조회
             System.out.println("content = " + driver1.findElement(By.name("description")).getAttribute("content"));

             //title 조회
             System.out.println("title = " + element.findElement(By.tagName("img")).getAttribute("alt"));
             //title className으로 조회했을 때
             //System.out.println("title = " + element.findElement(By.className("item__title")).getText());

             //img 조회
             System.out.println("image = " + element.findElement(By.tagName("img")).getAttribute("src"));
             //img className으로 조회했을 때
             //System.out.println("image = " + element.findElement(By.cssSelector("img.loaded")).getAttribute("src"));

             //tag 조회, 하위요소
             List<WebElement> tags = driver1.findElements(By.cssSelector("div.tag_wrap > div"));

             for (WebElement webElement : tags) {
                 System.out.print("tag = " + webElement.getText() + " / ");
             }
         }
         
         
         
         
         

      } catch (Exception e) {
         e.printStackTrace();
         driver.close();

      }
   }

}