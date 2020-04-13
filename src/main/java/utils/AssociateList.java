package utils;

import com.github.webdriverextensions.WebComponent;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import page.objects.NewCompanyModal;

public class AssociateList extends WebComponent {
    @FindBy(how = How.CSS, using = "mat-icon.delete")
    private WebElement delete;
    @FindBy(how = How.CSS, using = "input.wan-slider")
    private WebElement slider;
    @Getter
    @FindBy(how = How.CSS, using = "span.wan-value")
    private WebElement percentage;

    public NewCompanyModal deleteAssociate() {
        delete.click();

        return new NewCompanyModal(getWrappedDriver());
    }

    public NewCompanyModal slider(){
        int x=10;
        int width=slider.getSize().getWidth();
        Actions move = new Actions(getWrappedDriver());
        move.moveToElement(slider, ((width*x)/100), 0).click();
        move.build().perform();
        System.out.println(move);

        return new NewCompanyModal(getWrappedDriver());
    }
}
