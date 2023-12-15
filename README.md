# Automation Testing Project Readme
Overview
  Welcome to the API Testing Project! 
  This project is designed to facilitate automated testing of website, ensuring their functionality, reliability, and performance. 
  It is implemented as a Java Maven project using Eclipse. Auto-initialized files from Eclipse are skipped to focus on the main project files.

Project Structure
  The project is organized as follows:
    /src/.../testcase: This directory contains test cases files.
    /data: Contains Excel files with test data.
    /configuration: Configuration files for managing environment-specific variables.
    /src/.../automation/base: contains java files for basic setup method like driver instance.
    /src/.../automation/pom: contains java files for reuseable method for all automation testing function. 
    /src/.../automation/utils: Utility functions and helpers for common tasks.
    /src/.../automation/screenshot: contains screenshot images of all failure test cases.
    
How it works
  "propertiesFileUtils" java file in /src/.../utils/readFile used to get and save data for your testing. Ensure that it is written in key=value format.
  /src/.../automation/base/driverInstance.java will create a driver instance, open browser for automating and close browser after testing methods are done.
  Because this automation testing needs to obtain data from an excel file, you must have a data provider method like this one:
  
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
 
After obtaining data from using readFile.java and data provider, you calls all methods you need for automation testing in java files inside /src/.../automation/pom folder to create your automation testing. 
Note: In /src/.../automation/pom/login.java we using PageFactory for re-use web element, example:
	@FindBy(xpath = "//input[contains(@placeholder,'Email Address')]")
		private WebElement emailInput;
  	public login(WebDriver driver) {
			PageFactory.initElements(driver, this);
			wait = new WebDriverWait(driver, timeout);
			loginLink.click();
			wait.until(ExpectedConditions.visibilityOf(emailInput));
			Assert.assertEquals(true, emailInput.isDisplayed(), "Page load fail");
	}
 
Getting Started
	After clone this project to your local workspace:
	1. Open eclipse
	2. Click on "File" (alt+f)
	3.  Open project from file systems > Diretory > select project folder.
