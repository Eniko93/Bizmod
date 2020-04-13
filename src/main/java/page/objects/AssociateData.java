package page.objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import utils.AssociateList;
import utils.BaseWebComponent;
import utils.InputField;

import java.util.List;

public class AssociateData extends BaseWebComponent {
    @FindBy(how = How.CSS, using = "a.waves-effect")
    private WebElement addAssociateButton;
    @FindBy(how = How.CSS, using = "span[placeholder='Associate email']:empty")
    private InputField emptyAssociateEmail;
    @FindBy(how = How.CSS, using = "app-company-associates-list div.ng-star-inserted")
    private List<AssociateList> associates;
    @FindBy(how = How.CSS, using = "span[placeholder='Associate email']")
    private WebElement associateEmail;

    public AssociateData addAssociate(String email) {
        addAssociateButton.click();
        emptyAssociateEmail.fillIn(email);

        return this;
    }

    public String getAssociateName(int index) {
        return associates.get(index).getText();
    }

    public int getAssociateNumber() {
        return associates.size();
    }

    public AssociateList findAssociateByName(String associate) {
        return associates.stream().filter(a -> a.getText().contains(associate)).findAny()
                .orElseThrow(() -> new RuntimeException("Associate with name [" + associate + "] not found."));
    }
}
