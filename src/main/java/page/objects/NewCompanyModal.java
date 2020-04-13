package page.objects;

import lombok.Getter;
import models.CompanyModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.AssociateList;
import utils.InputField;

@Getter
public class NewCompanyModal extends BasePage {
    @FindBy(how = How.CSS, using = "input[placeholder='Company name']")
    private InputField companyName;
    @FindBy(how = How.CSS, using = "input[placeholder='Company motto']")
    private InputField companyMotto;
    @FindBy(how = How.XPATH, using = "(//input[@placeholder='Select an option'])[1]")
    private InputField country;
    @FindBy(how = How.CSS, using = ".btn-flat.back")
    private WebElement closeButton;
    @FindBy(how = How.CSS, using = ".next")
    private WebElement nextButton;
    @FindBy(how = How.XPATH, using = "(.//button[contains(@class,'ui-autocomplete-dropdown')])[1]")
    private WebElement countryDropdown;
    @FindBy(how = How.CSS, using = "textarea[placeholder='Company decription']")
    private InputField description;
    @FindBy(how = How.CSS, using = "button[class='mat-icon-button']")
    private WebElement calendar;
    @FindBy(how = How.CSS, using = "div.modal-body")
    private FiscalData fiscalData;
    @FindBy(how = How.CSS, using = "div.step-content")
    private AssociateData associateData;

    public NewCompanyModal(WebDriver driver) {
        super(driver, ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ul.steps")));
    }

    public NewCompanyModal fillInCompanyName(String name) {
        companyName.fillIn(name);

        return this;
    }

    public boolean isNameValid() {
        return companyName.isValid(driver);
    }

    public NewCompanyModal fillInCompanyMotto(String motto) {
        companyMotto.fillIn(motto);

        return this;
    }

    public boolean isMottoValid() {
        return companyMotto.isValid(driver);
    }

    public NewCompanyModal fillInFiscalName(String fiscalName) {
        fiscalData.fillInFiscalName(fiscalName);

        return this;
    }

    public boolean isFiscalNameValid() {
        return fiscalData.isFiscalNameValid();
    }

    public NewCompanyModal fillInCompanyID(String id) {
        fiscalData.fillInCompanyID(id);

        return this;
    }

    public boolean isIdValid() {
        return fiscalData.isIdValid();
    }

    public NewCompanyModal fillInBank(String bank) {
        fiscalData.fillInBank(bank);

        return this;
    }

    public boolean isBankValid() {
        return fiscalData.isBankValid();
    }

    public NewCompanyModal fillInIban (String iban) {
        fiscalData.fillInIban(iban);

        return this;
    }

    public boolean isIbanValid() {
        return fiscalData.isIbanValid();
    }

    public NewCompanyModal fillInCompanyHq(String hq) {
        fiscalData.fillInCompanyHq(hq);

        return this;
    }

    public boolean isCompanyHqValid() {
        return fiscalData.isCompanyHqValid();
    }

    public NewCompanyModal fillInRegistrationNumber(String number) {
        fiscalData.fillInRegistrationNumber(number);

        return this;
    }

    public boolean isRegistrationNumberValid() {
        return fiscalData.isRegistrationNumberValid();
    }

    public NewCompanyModal fillInDescription(String description) {
        this.description.fillIn(description);

        return this;
    }

    public CalendarModal openCalendar() {
        calendar.click();

        return new CalendarModal(driver);
    }

    public NewCompanyModal addAssociate(String email) {
        associateData.addAssociate(email);

        return this;
    }

    public NewCompanyModal next() {
        nextButton.click();

        return this;
    }

    public CompaniesPage save() {
        nextButton.click();

        return new CompaniesPage(driver);
    }

    public NewCompanyModal selectCountry(String country) {
        countryDropdown.click();
        fiscalData.findElementByName(country);

        return this;
    }

    public boolean isCountryValid() {
       return country.isValid(driver);
    }

    public NewCompanyModal selectCurrency(String currency) {
        fiscalData.selectCurrency(currency);

        return this;
    }

    public NewCompanyModal selectType(String type) {
        fiscalData.selectType(type);
        return this;
    }

    public NewCompanyModal fillInRequiredIntroForm(CompanyModel model) {
        fillInCompanyName(model.getName())
                .fillInCompanyMotto(model.getMotto())
                .selectCountry(model.getCountry());

        return this;
    }

    public NewCompanyModal fillInRequiredFiscalForm(CompanyModel model) {
         fillInFiscalName(model.getFiscalName())
                .fillInCompanyID(model.getId())
                .fillInRegistrationNumber(model.getRegistrationNumber())
                .fillInBank(model.getBank())
                .fillInIban(model.getIban())
                .fillInCompanyHq(model.getHq());

        return this;
    }

    public NewCompanyModal fillInIntroForm(CompanyModel model) {
        fillInCompanyName(model.getName())
                .fillInCompanyMotto(model.getMotto())
                .selectCountry(model.getCountry())
                .fillInDescription(model.getDescription());

        return this;
    }

    public NewCompanyModal fillInFiscalForm(CompanyModel model) {
        fillInFiscalName(model.getFiscalName())
                .openCalendar()
                .clickOnCurrentDate()
                .selectCurrency(model.getCurrency())
                .fillInCompanyID(model.getId())
                .fillInRegistrationNumber(model.getRegistrationNumber())
                .fillInBank(model.getBank())
                .fillInIban(model.getIban())
                .fillInCompanyHq(model.getHq())
                .selectType(model.getType());

        return this;
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
