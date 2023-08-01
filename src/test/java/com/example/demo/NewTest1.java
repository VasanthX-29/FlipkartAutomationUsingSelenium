package com.example.demo;

import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;


import io.github.bonigarcia.wdm.WebDriverManager;

public class NewTest1 {
	
 WebDriver wd=null;
	 
	 @BeforeTest
	  public void beforeTest() {
	    WebDriverManager.chromedriver().setup();	
		wd=new ChromeDriver();
		 
		//System.setProperty("webdriver.edge.driver","D:\\drivers\\msedgedriver.exe");	
		//wd=new EdgeDriver();
		//wd.manage().window().maximize();
	     
		
	  }
	 
	 @Test(priority=1)
	 public void launchFlipkartTest()
	 {
		 wd.get("https://www.flipkart.com/");
		 
		 Reporter.log("Flipkart website launched successfully!");
	
	 }
	 
	 @Test(priority=2)
	 public void performanceTest()
	 {
		 long starttime,endtime;
		
		 starttime=System.currentTimeMillis();
		 
		 wd.get("https://www.flipkart.com/");
		 
		 wd.manage().timeouts().implicitlyWait(5000,TimeUnit.MILLISECONDS);
		 
		 wd.findElement(By.name("q")).isDisplayed();
		 
		 endtime=System.currentTimeMillis();
		 
		 long pageloadtime=endtime-starttime;
		 
		 Reporter.log("Performnace Test Completed Successfully!");
		 
		 Reporter.log("Page Load Time: "+pageloadtime+" milliseconds");
		 
		
		 
	}
	 
	@Test(priority=3)
	public void searchProductTest()
	{
		 wd.manage().timeouts().implicitlyWait(8000,TimeUnit.MILLISECONDS);
		 
		 List<WebElement> dialog = wd.findElements(By.xpath("/html/body/div[2]/div/div/button"));

		 if (!dialog.isEmpty()) {
		   wd.findElement(By.xpath("/html/body/div[2]/div/div/button")).click();
		 } 
	
		
		wd.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div/div/div/div[1]/div/div[1]/div/div[2]/div[1]/div/div[1]/div/div/div/div/div[1]/a[2]/div/div/div/div/img")).click();
		
		wd.findElement(By.className("_3704LK")).sendKeys("iPhone 13");
		
		wd.findElement(By.xpath("/html/body/div/div/div[1]/div[1]/div[2]/div[2]/form/div/button")).click();
		
		Reporter.log("Search a product test completed successfully !");
		
		
	}
	 
	@Test(priority=4,dependsOnMethods = "searchProductTest")
	public void imageLoadTest()
	{
		wd.manage().timeouts().implicitlyWait(5000,TimeUnit.MILLISECONDS);
		
		String altTextToFind1 = "APPLE iPhone 13 (Blue, 128 GB)";
		
		boolean isImg1Displayed=wd.findElement(By.cssSelector("img[alt='"+ altTextToFind1+ "']")).isDisplayed();
		
        String altTextToFind2 = "APPLE iPhone 13 (Starlight, 128 GB)";
		
		boolean isImg2Displayed=wd.findElement(By.cssSelector("img[alt='"+ altTextToFind2+ "']")).isDisplayed();
		System.out.println(isImg1Displayed);
		System.out.println(isImg2Displayed);
		 if(isImg1Displayed && isImg2Displayed )
		 {
			 Reporter.log("Images loaded successfully till the screen height");
			 
			 
		 }
	}
	
	@Test(priority=5)
	public void imageDownloadedBeforeScrollTest()
	{
		 String altTextToFind = "APPLE iPhone 13 (Green, 128 GB)";
			
	      List<WebElement> isPresent = wd.findElements(By.cssSelector("img[alt='"+ altTextToFind+ "']"));

		 if (!isPresent.isEmpty()) {
		    Reporter.log("Image downloaded before the user scrolls!");
		  
		 }
		 else
		 {
			 Reporter.log("Image has not been downloaded ! ");
			
		 }
	}
	
	@Test(priority=6)
	public void scrollFeatureTest()
	{
		 JavascriptExecutor js=(JavascriptExecutor)wd;
		 js.executeScript("window.scrollBy(0,500)");
		 Reporter.log("This page has a scroll feature !");
		
	}
	@Test(priority=7)
	public void frequencyTest() throws InterruptedException
	{
		
            JavascriptExecutor jsExecutor = (JavascriptExecutor) wd;
            jsExecutor.executeScript("window.scrollBy(0, 500)");
            Thread.sleep(2000); 
            String altTextToFind = "APPLE iPhone 13 (Pink, 256 GB)";
			
  	        List<WebElement> isPresent = wd.findElements(By.cssSelector("img[alt='"+ altTextToFind+ "']"));

  		   if (!isPresent.isEmpty()) {
  		    Reporter.log("Image loaded successfully after first scroll !");
  		   
  		   }
  		   jsExecutor.executeScript("window.scrollBy(0, 500)");  
  		
  	       String altTextToFind2 = "APPLE iPhone 13 (Green, 256 GB)";
		
	       List<WebElement> isPresent2 = wd.findElements(By.cssSelector("img[alt='"+ altTextToFind2+ "']"));

		   if (!isPresent2.isEmpty()) {
		    Reporter.log("Image loaded successfully after second scroll!");
		   
		   }
          
       
	}
	
	@Test(priority=8)
	public void navigateToBottomFeatureTest()
	{
		 JavascriptExecutor js=(JavascriptExecutor)wd;
		 js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		 Reporter.log("Navigated to the bottom of the page !");

	}
	
	@AfterTest()
	public void afterTest() throws InterruptedException
	{
		Thread.sleep(2000);
		wd.close();
	}
	
	
	
 
}
