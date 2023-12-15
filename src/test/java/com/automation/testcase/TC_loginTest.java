package com.automation.testcase;

import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import com.automation.base.driverInstance;
import com.automation.pom.login;
import com.automation.utils.readFile;
import com.automation.utils.screenShot;

public class TC_loginTest extends driverInstance {

	private Object[][] data;
	@Test(dataProvider = "Excel")
	public void TC1_loginAccounts(String email, String password) throws InterruptedException {
		String url = readFile.getProperty("url");
		System.out.println("URL = " + url);
		driver.get(url);
		login login = new login(driver);
		PageFactory.initElements(driver, login);
		for (int i = 0; i < data.length; i++) {
			email = data[i][0].toString();
			System.out.println("username1 = " + email);
			password = data[i][1].toString();
			System.out.println("password1 = " + password);
			login.enterEmail(email);
			login.enterPassword(password);
			login.clickSignIn();
			login.clickLogout();
		}
	}

	@AfterMethod
	public void takeScreenshot(ITestResult result) throws InterruptedException {
		Thread.sleep(1000);
		if (ITestResult.FAILURE == result.getStatus()) {
			try {
				screenShot.takeScreenshot(driver, result.getName());
				System.out.println("Screenshot taken: " + result.getName());
			} catch (Exception e) {
				System.out.println("Exception while taking screenshot " + e.getMessage());
			}
		}
	}

	@Test
	@DataProvider(name = "Excel")
	public Object[][] testDataGenerator() throws IOException {
			FileInputStream file = new FileInputStream("E:/Eclipse_workspace/ASM2/data/data.xlsx");
			try (
				XSSFWorkbook workbook = new XSSFWorkbook(file)) {
				XSSFSheet loginSheet = workbook.getSheet("Login");
				if (loginSheet == null) {
					System.out.println("Sheet 'Login' not found in the Excel file.");
					return new Object[0][0]; // Return an empty array if the sheet is not found
				}
				int numRows = loginSheet.getPhysicalNumberOfRows();
				data = new Object[numRows][2];
				for (int i = 0; i < numRows; i++) {
					XSSFRow row = loginSheet.getRow(i);
					if (row != null) {
						XSSFCell email = row.getCell(0);
						XSSFCell password = row.getCell(1);
						if (email != null && password != null) {
							data[i][0] = email.getStringCellValue();
							data[i][1] = password.getStringCellValue();
						} else {
							System.out.println("Warning: Null cell encountered in row " + i);
							break;
						}
					} else {
						System.out.println("Warning: Null row encountered at index " + i);
						break;
					}
				}
			return data;
		} catch (IOException e) {
			System.out.println("Exception while reading Excel file: " + e.getMessage());
			throw e;
		}
	}
}
