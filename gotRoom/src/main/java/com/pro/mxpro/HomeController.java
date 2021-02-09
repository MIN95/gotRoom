package com.pro.mxpro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.pro.mxpro.commons.CrawlingHandler;

@Controller
public class HomeController {
	
	@Inject
	private CrawlingHandler crawlingHandler;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	private static ChromeDriver driver;
	
	@PostConstruct
	public void setDriver() {
		//드라이브 설정
		System.setProperty("webdriver.chrome.driver","C:\\java\\project\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
	    HashMap<String, Object> prefs = new HashMap<String, Object>();
	    prefs.put("profile.default_content_setting_values",2);
	    options.setExperimentalOption("prefs", prefs);
	    options.addArguments("headless","disable-gpu","no-sandbox");

		// 웹페이지 요청 
		ChromeDriver driver = new ChromeDriver( options );
		String location = "https://isale.land.naver.com/NewiSaleMobile/Home/#SYHome?SYMap=37.566427:126.977872:12&a=IA01:IA02:IC01&s=reg_date";
        driver.get(location);
        driver.manage().timeouts().implicitlyWait(4,TimeUnit.SECONDS);
		this.driver = driver;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home() throws Exception{ 
		ModelAndView mav = new ModelAndView();
		mav.setViewName("home");
		
		List<Map<String,String>> list = crawlingHandler.getMainData(driver);
		mav.addObject("list",list);
		List<Map<String,String>> newsList = crawlingHandler.getNews(driver);
		mav.addObject("newsList",newsList);
		return mav;
	}
	
}
