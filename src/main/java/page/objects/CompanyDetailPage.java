package page.objects;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.AssociateList;

@Getter
public class CompanyDetailPage extends BasePage {
    @FindBy(how = How.CSS, using = "h1.company-name")
    private WebElement name;
    @FindBy(how = How.CSS, using = "p.company-motto")
    private WebElement motto;
    @FindBy(how = How.CSS, using = ".company-description textarea")
    private WebElement description;
    @FindBy(how = How.CSS, using = "app-material.companyFiscal")
    private FiscalData fiscalData;
    @FindBy(how = How.CSS, using = "mat-icon.delete")
    private WebElement deleteButton;
    @FindBy(how = How.CSS, using = "button.btn-confirm")
    private WebElement confirmButton;
    @FindBy(how = How.CSS, using = "div.associates")
    private AssociateData associateData;


    public CompanyDetailPage(WebDriver driver) {
        super(driver, ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.mat-tab-links")));
    }

    public CompaniesPage deleteCompany() {
        deleteButton.click();
        confirmButton.click();

        return new CompaniesPage(driver);
    }

    public String getFiscalValue() {
        return fiscalData.getFiscalName().getValue();
    }

    public String getType() {
        return fiscalData.getType().getText();
    }

    public String getDateValue() {
        return fiscalData.getData().getValue();
    }

    public String getIdValue() {
        return fiscalData.getCompanyId().getValue();
    }

    public String getHqValue() {
        return fiscalData.getCompanyHq().getValue();
    }

    public String getBankValue() {
        return fiscalData.getBank().getValue();
    }

    public String getCurrency() {
        return fiscalData.getCurrency().getText();
    }

    public String getIbanValue() {
        return fiscalData.getIban().getValue();
    }

    public String getAssociateName(int index) {
        return associateData.getAssociateName(index);
    }

    public int getAssociateNumber() {
        return associateData.getAssociateNumber();
    }

    public AssociateList findAssociateByName(String associate) {
        return associateData.findAssociateByName(associate);
    }
}
