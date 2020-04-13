package page.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.InputField;

public class SignInPage extends BasePage {
    private static final String EMAIL_FIELd = "input[type='email']";

    @FindBy(how = How.CSS, using = EMAIL_FIELd)
    private InputField emailField;
    @FindBy(how = How.CSS, using = "input[type='password']")
    private InputField passwordField;
    @FindBy(how = How.CSS, using = "button[type='submit']")
    private WebElement loginButton;

    public SignInPage(WebDriver driver) {
        super(driver, ExpectedConditions.visibilityOfElementLocated(By.cssSelector(EMAIL_FIELd)));
    }

    public SignInPage fillInEmail(String emailAddresss) {
        emailField.fillIn(emailAddresss);

        return this;
    }

    public SignInPage fillInPassword(String password) {
        passwordField.fillIn(password);

        return this;
    }

    public BizmodPage pressLogin() {
        loginButton.click();

        return new BizmodPage(driver);
    }
}
