package page.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BizmodPage extends BasePage {
    @FindBy(how = How.CSS, using = ".desktop-navigation__item a[href='/app/companies']")
    private WebElement companies;

    public BizmodPage(WebDriver driver) {
        super(driver, ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".nav-wrapper")));
    }

    public CompaniesPage navigateToCompaniesPage() {
        companies.click();

        return new CompaniesPage(driver);
    }
}
