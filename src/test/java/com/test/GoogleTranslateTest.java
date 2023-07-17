package com.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.pages.GoogleTranslatePage;
import com.utils.ExcelUtil;
import io.github.bonigarcia.wdm.WebDriverManager;

public class GoogleTranslateTest {
	private WebDriver driver;
	private GoogleTranslatePage googleTranslatePage;

	@DataProvider(name = "excelData")
	public Object[][] getExcelData() throws Exception {
		return ExcelUtil.getTestData("./TestData/GoogleTranslate.xlsx");
	}

	@BeforeClass
	public void setUp() throws InterruptedException {
		 WebDriverManager.chromedriver().setup();
	        driver = new ChromeDriver();
	        googleTranslatePage = new GoogleTranslatePage(driver);
	        driver.get("https://translate.google.com/");
	        googleTranslatePage.clickSpanishButton();

	}

	@Test(dataProvider = "excelData")
	public void testTranslation(String testCase, String inputEnglish, String expectedSpanish) throws Exception {

		
		 googleTranslatePage.enterText(inputEnglish);
	        Thread.sleep(2000); // wait for translation
	        String actualSpanish = googleTranslatePage.getTranslatedText();

	        // assert and update the result in excel
	        try {
	            Assert.assertEquals(actualSpanish, expectedSpanish);
	            ExcelUtil.updateResult("./TestData/GoogleTranslate.xlsx", testCase, "PASS");
	        } catch (AssertionError e) {
	            ExcelUtil.updateResult("./TestData/GoogleTranslate.xlsx", testCase, "FAIL");
	        }
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
