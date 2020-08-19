package resources;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	
	public static WebDriver driver;
	static File f1=new File("./JSON/configuration.json");
	
	public static WebDriver getDriver()
	{
		JSONObject json=JSONReadFromFile();
		String browser=(String) json.get("browser");
		System.out.println(browser);
		if (browser.equals("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else {
			if (browser.equals("firefox"))
			{
				WebDriverManager.firefoxdriver().setup();
				driver=new FirefoxDriver();
			}
		else {
			if (browser.equals("internetExplorer"))
			{
				WebDriverManager.chromedriver().setup();
				driver=new InternetExplorerDriver();
			}
		else {
			System.out.println("Not a valid browser");
		}
			}
			}
		return driver;
		}
	//Read JSON file 
	public static JSONObject JSONReadFromFile()
	{
		JSONParser parser=new JSONParser();
		JSONObject jsonObject=null;
		try {
			Object obj=parser.parse(new FileReader(f1.getAbsoluteFile()));
			jsonObject=(JSONObject)obj;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}return jsonObject;
	}
	//To upload File using Robot class
	public static void uploadFile(File path)
	{
		try {
			Robot r=new Robot();
			r.setAutoDelay(3000);
			StringSelection selection=new StringSelection(path.getAbsolutePath());
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
			//Press the key
			r.keyPress(KeyEvent.VK_CONTROL);
			r.keyPress(KeyEvent.VK_V);
			//Release the key
			r.keyRelease(KeyEvent.VK_CONTROL);
			r.keyRelease(KeyEvent.VK_V);
			//Press Enter key
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	//To perform click operation 
	public static void btnClick(WebElement element)
	{
		element.click();
	}
	//To get all link(valid link)
	public static void validLink(List<WebElement> element)
	{
		try {
			System.out.println(element.size());
			for(int i=1;i<element.size();i++)
			{
				String links=element.get(i).getAttribute("href");
				if(links.equals("https://support.google.com/"))
				{
					System.out.println("Systems are working fine");
				}
				else 
					System.out.println("Not here to searching website");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
	}
}
	public static void getScreenShot(String screenShotFileName)
	{
		File screenShotLocation=new File("'/ScreenShot/"+screenShotFileName+".png");
		TakesScreenshot tk=(TakesScreenshot)driver;
		File src=tk.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(src,screenShotLocation);
			}catch(Exception e){
		e.printStackTrace();	
		}
		
	}
	public List<HashMap<String, String>> readValueFromExcel(){
		List<HashMap<String, String>> mapDataList = new ArrayList<HashMap<String,String>>();
		String sheetName="datas";
		try {
		File f=new File("./Excel/Sample.xsls");
		FileInputStream stream=new FileInputStream(f);
		Workbook w = new XSSFWorkbook(stream);
		Sheet s = w.getSheet(sheetName);
		Row headerRow=s.getRow(0);
		for(int i=0;i<s.getPhysicalNumberOfRows();i++)
		{
			Row currentRow = s.getRow(i);
			HashMap<String,String> mapData=new HashMap<String, String>();
			for(int j=0;j<headerRow.getPhysicalNumberOfCells();j++)
			{
				Cell currentCell = currentRow.getCell(j);
				switch(currentCell.getCellType()){
				case STRING:
					mapData.put(headerRow.getCell(j).getStringCellValue(),currentCell.getStringCellValue());
					break;
				case NUMERIC:
					mapData.put(headerRow.getCell(j).getStringCellValue(), String.valueOf(currentCell.getNumericCellValue()));
					break;
				default:
					break;
				}
				}
			mapDataList.add(mapData);
		}
		}catch(Exception e) {
		e.printStackTrace();
	}
		return mapDataList;

	}
}
