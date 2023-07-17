package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleTranslatePage {
	private WebDriver driver;
	private By sourceTextField = By.xpath("//textarea[@aria-label='Source text']");
	private By resultBox = By.xpath("//span[@class='ryNqvb']");
	private By spanishButton = By.xpath("(//span[text()='Spanish'])[2]/../..");

	public GoogleTranslatePage(WebDriver driver) {
		this.driver = driver;
	}

	public void enterText(String text) throws InterruptedException {
		 WebDriverWait wait = new WebDriverWait(driver, 10);
		    WebElement source = wait.until(ExpectedConditions.elementToBeClickable(sourceTextField));
		    source.clear();
		    source.sendKeys(text);
		    Thread.sleep(3000);
	}

	public String getTranslatedText() {
		WebElement result = driver.findElement(resultBox);
		return result.getText();
	}

	public void clickSpanishButton() throws InterruptedException {
	    Thread.sleep(3000); // Adjust the delay as needed
	    driver.findElement(spanishButton).click();
	}
}
