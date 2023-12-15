package com.automation.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;

public class screenShot {
	public static void takeScreenshot(WebDriver driver, String testcaseName) {
		try {
			String imageName = testcaseName+".png";

			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File source = screenshot.getScreenshotAs(OutputType.FILE);

			File destiny = new File("E:/Eclipse_workspace/ASM2/src/screenShot/" + imageName);
			FileHandler.copy(source, destiny);

		} catch (Exception ex) {
			System.out.println("Đã xảy ra lỗi khi chụp màn hình!");
			ex.printStackTrace();
		}
	}

}
