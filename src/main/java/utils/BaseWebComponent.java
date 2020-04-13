package utils;

import com.github.webdriverextensions.WebComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsDriver;
import org.openqa.selenium.internal.WrapsElement;

public class BaseWebComponent<THIS extends BaseWebComponent> extends WebComponent {

    @Override
    public WebDriver getWrappedDriver() {
        WebElement element = this.getWrappedWebElement();
        if (WrapsElement.class.isAssignableFrom(element.getClass())) {
            return ((WrapsDriver) ((WrapsElement) element).getWrappedElement()).getWrappedDriver();
        }

        return super.getWrappedDriver();
    }
}

