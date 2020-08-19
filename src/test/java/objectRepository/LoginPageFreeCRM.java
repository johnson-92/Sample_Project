package objectRepository;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import resources.TestBase;

public class LoginPageFreeCRM extends TestBase {

	public LoginPageFreeCRM()
	{
		PageFactory.initElements(driver, this);
	}
	@FindBy(name="q")
	private WebElement txtSearch;
	
	@FindBy(name="btnK")
	private WebElement btnClk;
	
	@FindBy(tagName="a")
	private List<WebElement> allLink;
	
	public List<WebElement> getAllLink() {
		return allLink;
	}
	public WebElement getBtnClk() {
		return btnClk;
	}
	public void setBtnClk(WebElement btnClk) {
		this.btnClk = btnClk;
	}
	public WebElement getTxtSearch() {
		return txtSearch;
	}
	public void setTxtSearch(WebElement txtSearch) {
		this.txtSearch = txtSearch;
	}
	
}
