package utils;

import com.github.webdriverextensions.WebComponent;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class InputField extends WebComponent {

    public boolean isValid(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        return (boolean) js.executeScript("return arguments[0].checkValidity();", this.getWrappedWebElement());
    }

    public String getValidationMessage() {
        return this.getAttribute("validationMessage");
    }

    public String getValue() {
        return getWrappedWebElement().getAttribute("value");
    }

    public void fillIn(String text) {
        getWrappedWebElement().clear();
        getWrappedWebElement().sendKeys(text);
    }
}
