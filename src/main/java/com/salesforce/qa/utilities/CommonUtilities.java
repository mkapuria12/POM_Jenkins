package com.salesforce.qa.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.salesforce.qa.base.TestBase;
import com.salesforce.qa.pages.LoginPage;

public class CommonUtilities extends TestBase{
	
	public static String TESTDATA_SHEET_PATH = "/Users/Mit/eclipse-workspace/Demo_POM_Framework/src/main/java/com/salesforce/qa/testData/SalesforceTestData.xlsx";
	public static WebDriver driver;
	static Workbook book;
	static Sheet sheet;
	
	
	public static Object[][] readDataFromExcelSheet(String sheetName) throws Exception {
		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		// sheet.getRow(0).getLastCellNum());
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
			}
		}
		return data;
		}

	public static String takeScreenshotAtEndOfTest(WebDriver driver) throws IOException {
		
		TakesScreenshot ts = (TakesScreenshot)driver; //Screenshot setup is done
		File Source = ts.getScreenshotAs(OutputType.FILE);//Taken the screenshot and saving it to source
		String sPathOfDestinationImage = System.getProperty("user.dir")+"/Screenshots/"+ 
				new SimpleDateFormat("'Image_'YYYYMMddHHmm'.png'").format(new Date());
		File dest = new File(sPathOfDestinationImage);
		FileUtils.copyFile(Source, dest);
		return sPathOfDestinationImage;
	}
	
}
