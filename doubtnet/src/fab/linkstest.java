package fab;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class linkstest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/chromedriver"); 
		
		
		WebDriver driver = new ChromeDriver();
/*lists of all links*/
		driver.get("https://doubtnut.com");
		
		
		List<WebElement> LinksList = driver.findElements(By.tagName("a"));
		//LinksList.addAll(driver.findElements(By.tagName("img")));

		System.out.println("total links size -->" +LinksList.size());
		for(int i=0; i<LinksList.size(); i++)
		{
		//System.out.println(LinksList.get(i).getAttribute("href"));
			System.out.println(LinksList.get(i).getText());
		}


		//**** ADD ALL VALID LINKS TO A ANOTHER LIST ****

		List<WebElement> activeLinks = new ArrayList<WebElement>();

		for(int i=0; i<LinksList.size(); i++)
		{
		//System.out.println(LinksList.get(i).getAttribute("href"));

		if(LinksList.get(i).getAttribute("href") != null && (! LinksList.get(i).getAttribute("href").contains("javascript:void(0)")))
		{
		activeLinks.add(LinksList.get(i));
		}
		}
		System.out.println("Active links size -->" +activeLinks.size());


		//**** CHECK URL WITH HTTP CONNECTION API ****
		try
		{
		for(int j=0; j<activeLinks.size(); j++)
		{

		HttpURLConnection connection = (HttpURLConnection) new URL(activeLinks.get(j).getAttribute("href")).openConnection();
		connection.setConnectTimeout(3000);
		connection.connect();
		String Response = connection.getResponseMessage();
		//connection.disconnect();

		System.out.println(activeLinks.get(j).getAttribute("href") +"---->" +Response);
		
		}
		
		}
		catch(Exception e)
		{
		System.out.println(e);

		}
		driver.quit();
		}



		

	

}


