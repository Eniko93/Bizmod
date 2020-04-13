package page.objects;

import com.github.webdriverextensions.WebDriverExtensionFieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.function.Function;

public class BasePage<THIS extends BasePage> {

    protected WebDriver driver;

    //constructor contains wait for page to be rendered.
    public BasePage(WebDriver driver, Function<? super WebDriver, ?> wait) {
        this.driver = driver;
        PageFactory.initElements(new WebDriverExtensionFieldDecorator(driver), this);
        this.waitUntil(wait);

    }

    //waitUntil function is inherited by all subsequent pages.
    protected <V> void waitUntil(Function<? super WebDriver, V> isTrue) {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        wait.until(isTrue);
    }
}