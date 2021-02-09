package com.pro.mxpro.commons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Component;

@Component
public class CrawlingHandler{
	
	public static List<Map<String,String>> getMainData(ChromeDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		String order = "$('.Item .NoImage').append('<div class=\"Thumbnail\" style=\"background-image:url(/resources/img/no_img.jpg);\"></div>');";
		js.executeScript(order); 
        // 데이터가져오기
		List<WebElement> realtyUrl = driver.findElementsByCssSelector("div.HomeListWrap  div.Item > a");
		// no-image 처리
        List<WebElement> realtyName = driver.findElementsByCssSelector("div.HomeListWrap  div.TitleArea > span");
        List<WebElement> pic = driver.findElementsByCssSelector("div.HomeListWrap .ItemInner div.Thumbnail");
        List<WebElement> price = driver.findElementsByCssSelector("div.HomeListWrap  div.PriceArea");
        List<WebElement> addr = driver.findElementsByCssSelector("div.HomeListWrap  div.InfoArea p:nth-child(1) span");
        List<WebElement> type = driver.findElementsByCssSelector("div.HomeListWrap  div.InfoArea p:nth-child(2) span:nth-child(1)");
        List<WebElement> totalNo = driver.findElementsByCssSelector("div.HomeListWrap  div.InfoArea p:nth-child(2) span:nth-child(2)");
        List<WebElement> scheduleType = driver.findElementsByCssSelector("div.HomeListWrap  div.ScheduleInfo span:nth-child(1)>span");
        List<WebElement> scheduleInfo = driver.findElementsByCssSelector("div.HomeListWrap  div.ScheduleInfo span:nth-child(2)");
        
        List<Map<String,String>> list = new ArrayList<Map<String,String>>();
        if( realtyName.size() !=0  ) {
        	for(int i=0;i<realtyName.size();i++) {
        		Map<String,String> map = new HashMap<String, String>();
        		map.put("url",realtyUrl.get(i).getAttribute("href"));
        		map.put("pic",pic.get(i).getAttribute("style"));
        		map.put("name",realtyName.get(i).getText());
        		map.put("price",price.get(i).getText());
        		map.put("addr",addr.get(i).getText());
        		map.put("type",type.get(i).getText());
        		map.put("totalNo",totalNo.get(i).getText());
        		map.put("scheduleType",scheduleType.get(i).getText());
        		map.put("scheduleInfo",scheduleInfo.get(i).getText());
        		list.add(map); 
        	}  
        }
//        driver.quit();
		return list;
	}
	
	public List<Map<String,String>> getNews(ChromeDriver driver) {
		driver.findElementByXPath("//*[@id=\"tab2\"]").click();
		List<WebElement> newsItem = driver.findElementsByCssSelector("div._hotIssueList:nth-child(1) div._newsListWrapper a");
        List<WebElement> title = driver.findElementsByCssSelector("div._hotIssueList:nth-child(1) div._newsListWrapper div.text");
        List<WebElement> source = driver.findElementsByCssSelector("div._hotIssueList:nth-child(1) div._newsListWrapper div.source");
        List<WebElement> thumbnail = driver.findElementsByCssSelector("div._hotIssueList:nth-child(1) div._newsListWrapper div.news_thumbnail span");
        List<Map<String,String>> list = new ArrayList<Map<String,String>>();
        if( newsItem.size() !=0  ) {
        	for(int i=0;i<newsItem.size();i++) {
        		Map<String,String> map = new HashMap<String, String>();
        		map.put("articleid",newsItem.get(i).getAttribute("_articleid"));
        		map.put("officeid",newsItem.get(i).getAttribute("_officeid"));
        		map.put("date",newsItem.get(i).getAttribute("_date"));
        		map.put("title",title.get(i).getText());
        		map.put("source",source.get(i).getText());
        		map.put("thumbnail",thumbnail.get(i).getAttribute("style"));
        		list.add(map); 
        	}  
        }
        driver.findElementByXPath("//*[@id=\"tab2\"]").click();
		return list;
	}
	
}