package page.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.CompanyList;

import java.util.List;

public class CompaniesPage extends BasePage {
    @FindBy(how = How.CSS, using = "div.fixed-action-btn")
    private WebElement addButon;
    @FindBy(how = How.XPATH, using = ".//app-card[contains(@class, 'companies-item')]")
    private List<CompanyList> companyItems;

    public CompaniesPage(WebDriver driver) {
        super(driver, ExpectedConditions.visibilityOfElementLocated(By.cssSelector("section.companies-list")));
    }

    public NewCompanyModal createNewCompany() {
        addButon.click();

        return new NewCompanyModal(driver);
    }

    public CompanyDetailPage navigateToTheCompanyDetailPage(String name) {
        findCompanyByName(name).click();

        return new CompanyDetailPage(driver);
    }

    public CompanyList findCompanyByName(String name) {
        return companyItems.stream().filter(r -> r.findElement(By.className("card-title")).getText().equals(name)).findAny().orElseThrow(() -> new RuntimeException("Company not found"));
    }
}
