package page.objects;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import utils.BaseWebComponent;
import utils.InputField;

import java.util.List;

@Getter
public class FiscalData extends BaseWebComponent {
    @FindBy(how = How.CSS, using = "input[placeholder='Company fiscal name']")
    private InputField fiscalName;
    @FindBy(how = How.CSS, using = "input[placeholder='Company Id']")
    private InputField companyId;
    @FindBy(how = How.CSS, using = "input[placeholder='Bank']")
    private InputField bank;
    @FindBy(how = How.CSS, using = "input[placeholder='IBAN']")
    private InputField iban;
    @FindBy(how = How.CSS, using = "input[placeholder='Company HQ']")
    private InputField companyHq;
    @FindBy(how = How.CSS, using = "input[placeholder='Company registration number']")
    private InputField registrationNumber;
    @FindBy(how = How.XPATH, using = "(.//button[contains(@class,'ui-autocomplete-dropdown')])[2]")
    private WebElement currencyDropdown;
    @FindBy(how = How.XPATH,  using = "(.//button[contains(@class,'ui-autocomplete-dropdown')])[3]")
    private WebElement typeDropdown;
    @FindBy(how = How.CSS, using = "li[class*='ng-tns-']")
    private List<WebElement> list;
    @FindBy(how = How.XPATH, using = "(.//span[contains(@class,'ng-tns-')])[2]")
    private WebElement type;
    @FindBy(how = How.XPATH, using = "(.//span[contains(@class,'ng-tns-')])[4]")
    private WebElement currency;
    @FindBy(how = How.CSS, using = "input[placeholder='Founding date']")
    private InputField data;

    public FiscalData fillInFiscalName(String fiscalName) {
        this.fiscalName.fillIn(fiscalName);

        return this;
    }

    public boolean isFiscalNameValid() {
        return fiscalName.isValid(getWrappedDriver());
    }

    public FiscalData fillInCompanyID(String id) {
        companyId.fillIn(id);

        return this;
    }

    public boolean isIdValid() {
        return companyId.isValid(getWrappedDriver());
    }

    public FiscalData fillInBank(String bank) {
        this.bank.fillIn(bank);

        return this;
    }

    public boolean isBankValid() {
        return bank.isValid(getWrappedDriver());
    }

    public FiscalData fillInIban (String iban) {
        this.iban.fillIn(iban);

        return this;
    }

    public boolean isIbanValid() {
        return iban.isValid(getWrappedDriver());
    }

    public FiscalData fillInCompanyHq(String hq) {
        companyHq.fillIn(hq);

        return this;
    }

    public boolean isCompanyHqValid() {
        return companyHq.isValid(getWrappedDriver());
    }

    public FiscalData fillInRegistrationNumber(String number) {
        registrationNumber.fillIn(number);

        return this;
    }

    public boolean isRegistrationNumberValid() {
        return registrationNumber.isValid(getWrappedDriver());
    }

    public FiscalData selectCurrency(String currency) {
        currencyDropdown.click();
        findElementByName(currency).click();

        return this;
    }

    public FiscalData selectType(String type) {
        typeDropdown.click();
        findElementByName(type).click();

        return this;
    }

    public WebElement findElementByName(String name) {
        return list.stream().filter(c -> c.getText().contains(name)).findAny()
                .orElseThrow(() -> new RuntimeException("Country with name [" + name + "] not found."));
    }
}

