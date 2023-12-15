package com.automation.pom;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
public class login {


		private WebDriverWait wait;
		private Duration timeout = Duration.ofSeconds(30);

		@FindBy(xpath = "//a[@href='/login']")
		private WebElement loginLink;

		@FindBy(xpath = "//input[contains(@placeholder,'Email Address')]")
		private WebElement emailInput;

		@FindBy(name = "email")
		private WebElement inputEmail;

		@FindBy(name = "password")
		private WebElement inputPassword;

		@FindBy(xpath = "//button[contains(@data-qa,'login-button')]")
		private WebElement loginButton;

		@FindBy(xpath = "//a[@href='/logout']")
		private WebElement logoutLink;

		public login(WebDriver driver) {
			PageFactory.initElements(driver, this);
			wait = new WebDriverWait(driver, timeout);
			loginLink.click();
			wait.until(ExpectedConditions.visibilityOf(emailInput));
			Assert.assertEquals(true, emailInput.isDisplayed(), "Page load fail");
		}

		public void enterEmail(String email) throws InterruptedException {
			wait.until(ExpectedConditions.visibilityOf(inputEmail));
			Assert.assertEquals(true, inputEmail.isDisplayed(), "Input email field not available.");
			inputEmail.sendKeys(email);
			Thread.sleep(2000);
		}

		public void enterPassword(String password) throws InterruptedException {
			wait.until(ExpectedConditions.visibilityOf(inputPassword));
			Assert.assertEquals(true, inputPassword.isDisplayed(), "Input password field not available.");
			inputPassword.sendKeys(password);
			Thread.sleep(2000);
		}

		public void clickSignIn() throws InterruptedException {
			wait.until(ExpectedConditions.visibilityOf(loginButton));
			Assert.assertEquals(true, loginButton.isDisplayed(), "Login button field not available.");
			loginButton.click();
			wait.until(ExpectedConditions.visibilityOf(logoutLink));
			Assert.assertEquals(true, logoutLink.isDisplayed(), "Login failed");
		}
		public void clickLogout() throws InterruptedException {
			logoutLink.click();
			Thread.sleep(5000);
		}

	}
