package utils;

import com.github.webdriverextensions.WebComponent;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

@Getter
public class CompanyList extends WebComponent {
    @FindBy(how = How.CLASS_NAME, using = "card-title")
    private WebElement companyName;
    @FindBy(how = How.CLASS_NAME, using = "card-subtitle")
    private WebElement motto;
}
