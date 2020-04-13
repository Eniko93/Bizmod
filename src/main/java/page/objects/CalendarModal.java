package page.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Utils;

import java.util.List;

public class CalendarModal extends BasePage {
    private static final String DAYS = "div.mat-calendar-body-cell-content";

    @FindBy(how = How.CSS, using = DAYS)
    private List<WebElement> days;

    public CalendarModal(WebDriver driver) {
        super(driver, ExpectedConditions.visibilityOfElementLocated(By.cssSelector(DAYS)));
    }

    private void clickOnDate(Integer day) {
        days.stream().filter(d -> d.getText().equals(String.valueOf(day))).findFirst()
                .orElseThrow(() -> new RuntimeException("Day [" + day + "] was not found"))
                .click();
    }

    public NewCompanyModal clickOnCurrentDate() {
        Integer today = Utils.getCurrentDate();
        clickOnDate(today);

        return new NewCompanyModal(driver);
    }
}
