package simpleDemo;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class simpleTest {

	WebDriver driver;
	
	@BeforeTest
	public void InitializeDriver(){
		String PropertyValue = "webdriver.chrome.driver";
		String ChromePath = "lib\\chromedriver.exe";
		
		System.setProperty(PropertyValue,ChromePath);
		
		ChromeOptions cOptions = new ChromeOptions();
		cOptions.setExperimentalOption("useAutomationExtension", false);
		cOptions.setExperimentalOption("excludeSwitches",
				Collections.singleton("enable-automation"));
		
		Map<String,Object> prefs = new HashMap<String,Object>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		cOptions.setExperimentalOption("prefs", prefs);
		
		driver = new ChromeDriver(cOptions);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@AfterTest
	public void QuitDriver(){
		driver.quit();
	}
	
	//@Test
	public void Test1(){
		List<String> monthList =Arrays.asList("January","Feburary","March","April","May","June","July","August","September","October","November","December");
		
		String departDateXpath="//div[@id='marketDate_1']//button[@class='ui-datepicker-trigger']";
		
		String baseURL= "https://book.spicejet.com/";
		driver.navigate().to(baseURL);
		
		WebElement departDate = driver.findElement(By.xpath(departDateXpath));
		departDate.click();
		int expMonth = 11;
		String expDate = "29";
		
		String calMonth = driver.findElement(By.xpath("//div[@class='ui-datepicker-group ui-datepicker-group-first']//span[@class='ui-datepicker-month']")).getText();
		
		if(monthList.indexOf(calMonth)+1 == expMonth){
			selectDate(expDate,"first");
		}else if(monthList.indexOf(calMonth)+2 == expMonth){
			selectDate(expDate,"last");
		}else{
			
		}
		
	}
	
	public void selectDate(String date,String datepickerGroup){
		List<WebElement> NoOfColums;
		
		WebElement datePicker = driver.findElement(By.xpath("//div[@id='ui-datepicker-div']//div[@class='ui-datepicker-group ui-datepicker-group-"+datepickerGroup+"']"));
		NoOfColums = datePicker.findElements(By.tagName("td"));
		
		for(WebElement cell : NoOfColums){
			if(cell.getText().equalsIgnoreCase(date)){
				cell.findElement(By.linkText(date)).click();
				break;
			}
		}
	}

	//@Test
	public void Test7(){
		String baseURL= "http://in.rediff.com/";
		driver.navigate().to(baseURL);
		
		WebElement searchBox = driver.findElement(By.id("srchword"));
		searchBox.sendKeys("h");
		searchBox.sendKeys(Keys.ENTER);
		
		
		List<WebElement> NoOfLinks = driver.findElements(By.xpath("//div[@class='div_myitemname']"));

		
		for(WebElement cell : NoOfLinks){
			System.out.println("--------------------------------");
			System.out.println(cell.getText());
		}
		
		
		
	}
	
	//@Test
	public void Test8(){
		String baseURL= "https://www.google.com/";
		driver.navigate().to(baseURL);
		
		WebElement searchBox = driver.findElement(By.id("lst-ib"));
		searchBox.sendKeys("eclipse");
		searchBox.sendKeys(Keys.ENTER);
		
		WebElement searchResult = driver.findElement(By.id("ires"));
		
		List<WebElement> NoOfLinks = searchResult.findElements(By.className("g"));
		
		for(WebElement cell : NoOfLinks){
			System.out.println("--------------------------------");
			System.out.println(cell.getText());
		}
		
		
		
	}
	
	//@Test
	public void Test2(){
		String baseURL= "https://www.yatra.com/";
		driver.navigate().to(baseURL);
		
		WebElement buses = driver.findElement(By.id("booking_engine_buses"));
		buses.click();
		
		WebElement source = driver.findElement(By.id("BE_bus_from_station"));
		source.sendKeys("Bangalore");
		
		WebElement destination = driver.findElement(By.id("BE_bus_to_station"));
		destination.sendKeys("Hyderabad");
		destination.sendKeys(Keys.ENTER);
	}
	
	//@Test
	public void Test9(){
		String baseURL= "https://www.hdfcbank.com/";
		driver.navigate().to(baseURL);
		
		WebElement creditCards = driver.findElement(By.xpath("//img[contains(@src,'credit-cards')]"));
		creditCards.click();

	}

	@Test
	public void Test6(){
		String baseURL= "http://newtours.demoaut.com/index.php";
		driver.navigate().to(baseURL);
		
		List<WebElement> NoOfColums;
		
		
		NoOfColums = driver.findElements(By.tagName("td"));
		
		for(WebElement cell : NoOfColums){
			if(cell.getText().equalsIgnoreCase("New York to Chicago")){
				System.out.println(cell.getText());
				break;
			}
			
		}

	}
	
	//@Test
	public void Test5(){
		String FILE_NAME = "sampleExcel.xlsx";
		
		 FileInputStream excelFile;
		try {
			excelFile = new FileInputStream(new File(FILE_NAME));
			Workbook workbook = new XSSFWorkbook(excelFile);
	         Sheet datatypeSheet = workbook.getSheetAt(0);
	         Iterator<Row> iterator = datatypeSheet.iterator();
	         while (iterator.hasNext()) {

	                Row currentRow = iterator.next();
	                Iterator<Cell> cellIterator = currentRow.iterator();

	                while (cellIterator.hasNext()) {

	                    Cell currentCell = cellIterator.next();

	                    if (currentCell.getCellTypeEnum() == CellType.STRING) {
	                        System.out.print(currentCell.getStringCellValue() + "--");
 
	                    } 

	                }
	                System.out.println();

	            }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
		
		
	}
	
	

}
