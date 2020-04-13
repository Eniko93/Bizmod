import models.CompanyModel;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.objects.BizmodPage;
import page.objects.CompaniesPage;
import page.objects.CompanyDetailPage;
import page.objects.NewCompanyModal;
import utils.CompanyList;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This test class includes the following test scenarios:
 * <p>
 * Create company with required data only and verify if appear on the companies list
 * Create company with all data and verify if data appear correctly on the detail page
 * Should validate the required fields for company creation
 * Delete a company from list
 * Add and delete associate during company creation
 * Move associate slider
 */

public class CompaniesTest extends BaseTest {
    @BeforeMethod(description = "Navigate to the companies page before every test")
    public void navigateToCompaniesPage() {
        new BizmodPage(driver)
                .navigateToCompaniesPage();
    }

    @Test(description = "Create company with required data only and verify if appear on the companies list")
    public void createCompany() {
        CompanyModel model = new CompanyModel("ALT", "Hello world",
                "Romania", "SRL", "A", "12345", "ING", "ING12345678", "erty");

        CompaniesPage companiesPage = new CompaniesPage(driver)
                .createNewCompany()
                .fillInRequiredIntroForm(model)
                .next()
                .fillInRequiredFiscalForm(model)
                .next()
                .save();

        CompanyList company = companiesPage.findCompanyByName(model.getName());
        Assert.assertEquals(company.getCompanyName().getText(), model.getName(), "The company's name is not correct.");
        Assert.assertEquals(company.getMotto().getText(), model.getMotto(), "The company's motto is not correct.");
    }

    @Test(description = "Create company with all data and verify if data appear correctly on the detail page")
    public void createCompanyWithAllData() {
        CompanyModel model = new CompanyModel("New company", "Motto","Romania", "Description 2", "N/A", "",
                "RON", "6", "12345", "BT", "BT12345", "HJ", "PFA");

        CompaniesPage companiesPage = new CompaniesPage(driver)
                .createNewCompany()
                .fillInIntroForm(model)
                .next()
                .fillInFiscalForm(model)
                .next()
                .addAssociate("associate@email")
                .save();

        CompanyDetailPage companyDetailPage = companiesPage
                .navigateToTheCompanyDetailPage(model.getName());

        Assert.assertEquals(companyDetailPage.getName().getText(), model.getName(), "The company's name is not correct.");
        Assert.assertEquals(companyDetailPage.getMotto().getText(), "``" + model.getMotto() + "``" , "The company's motto is not correct.");
        Assert.assertEquals(companyDetailPage.getDescription().getAttribute("value"), model.getDescription(), "The company's description is not correct.");
        Assert.assertTrue(companyDetailPage.getAssociateName(0).contains("associate@email"), "The associate is not correct.");
        Assert.assertEquals(companyDetailPage.getFiscalValue(), model.getFiscalName(), "The company's fiscal name is not correct.");
        Assert.assertEquals(companyDetailPage.getType(), model.getType(), "The company's type is not correct.");
        Assert.assertEquals(companyDetailPage.getDateValue(), new SimpleDateFormat("dd/MM/yyyy").format(new Date()), "The company's foundation date is not correct.");
        Assert.assertEquals(companyDetailPage.getIdValue(), model.getId(), "The company's id is not correct.");
        Assert.assertEquals(companyDetailPage.getHqValue(), model.getHq(), "The company's hq is not correct.");
        Assert.assertEquals(companyDetailPage.getBankValue(), model.getBank(), "The bank is not correct.");
        Assert.assertEquals(companyDetailPage.getIbanValue(), model.getIban(), "The iban is not correct.");
        Assert.assertEquals(companyDetailPage.getCurrency(), model.getCurrency(), "The currency is not correct.");
    }

    @Test(description = "Should validate the required fields for company creation")
    public void validation() {
        NewCompanyModal newCompanyModal = new CompaniesPage(driver)
                .createNewCompany()
                .next();

        Assert.assertFalse(newCompanyModal.isNameValid(), "The name field was not required.");
        Assert.assertFalse(newCompanyModal.isMottoValid(), "The motto field was not required.");
        Assert.assertFalse(newCompanyModal.isCountryValid(), "The country was not required.");

        newCompanyModal
                .fillInCompanyName("Valid name")
                .fillInCompanyMotto("Valid motto")
                .selectCountry("Romania")
                .next()
                .next();

        Assert.assertFalse(newCompanyModal.isFiscalNameValid(), "The fiscal name field was not required.");
        Assert.assertFalse(newCompanyModal.isIdValid(), "The id field was not required.");
        Assert.assertFalse(newCompanyModal.isRegistrationNumberValid(), "The registration number field was not required.");
        Assert.assertFalse(newCompanyModal.isBankValid(), "The bank field was not required.");
        Assert.assertFalse(newCompanyModal.isIbanValid(), "The iban field was not required.");
        Assert.assertFalse(newCompanyModal.isCompanyHqValid(), "The company hq field was not required.");
    }


    @Test(description = "Delete a company from list")
    public void deleteCompany() {
        CompanyModel model = new CompanyModel("Delete", "DeleteCompany",
                "Romania", "SRL", "A", "12345", "ING", "ING12345678", "erty");

        CompaniesPage companiesPage = new CompaniesPage(driver)
                .createNewCompany()
                .fillInRequiredIntroForm(model)
                .next()
                .fillInRequiredFiscalForm(model)
                .next()
                .save();

       companiesPage
                .navigateToTheCompanyDetailPage(model.getName())
                .deleteCompany();

       //this test fails because after deleting a company I am not redirected to an other page and company data's still exists
       Assert.assertEquals(companiesPage.findCompanyByName(model.getName()), model.getName(), "The company still exists");
    }

    @Test(description = "Add and delete associate during company creation")
    public void addAndDeleteAssociate() {
        final String firstAssociate = "first@associate";
        final String secondAssociate = "secondt@associate";
        final String thirdAssociate = "third@associate";

        CompanyModel model = new CompanyModel("Associate", "Associate test",
                "Romania", "SRL", "A", "12345", "ING", "ING12345678", "erty");

        NewCompanyModal newCompanyModal = new CompaniesPage(driver)
                .createNewCompany()
                .fillInRequiredIntroForm(model)
                .next()
                .fillInRequiredFiscalForm(model)
                .next()
                .addAssociate(firstAssociate)
                .addAssociate(secondAssociate)
                .addAssociate(thirdAssociate)
                .findAssociateByName(secondAssociate)
                .deleteAssociate();

        Assert.assertEquals(newCompanyModal.getAssociateNumber(), 2, "The associate number is not correct");
        Assert.assertTrue(newCompanyModal.getAssociateName(0).contains(firstAssociate), "The associate name is not correct");
        Assert.assertTrue(newCompanyModal.getAssociateName(1).contains(thirdAssociate), "The associate name is not correct");

        newCompanyModal
                .save();

        CompanyDetailPage companyDetailPage = new CompaniesPage(driver)
                .navigateToTheCompanyDetailPage(model.getName());

        //this test fails because appear 3 associate on the detail page
        Assert.assertEquals(companyDetailPage.getAssociateNumber(), 2, "The associate number is not correct");
        Assert.assertTrue(companyDetailPage.getAssociateName(0).contains(firstAssociate), "The associate name is not correct");
        Assert.assertTrue(companyDetailPage.getAssociateName(1).contains(thirdAssociate), "The associate name is not correct");
    }

    @Test(description = "Move associate slider")
    public void moveSlider() {
        final String firstAssociate = "first@associate";
        final String secondAssociate = "second@associate";

        CompanyModel model = new CompanyModel("Slider", "Associate test",
                "Romania", "SRL", "A", "12345", "ING", "ING12345678", "erty");

        NewCompanyModal newCompanyModal = new CompaniesPage(driver)
                .createNewCompany()
                .fillInRequiredIntroForm(model)
                .next()
                .fillInRequiredFiscalForm(model)
                .next()
                .addAssociate(firstAssociate)
                .addAssociate(secondAssociate)
                .findAssociateByName(firstAssociate)
                .slider()
                .findAssociateByName(secondAssociate)
                .slider();

        Assert.assertEquals(newCompanyModal.getAssociateNumber(), 2, "The associate number is not correct");
        Assert.assertEquals(newCompanyModal.findAssociateByName(firstAssociate).getPercentage().getText(), "61%", "The percentage is not 61");
        Assert.assertEquals(newCompanyModal.findAssociateByName(secondAssociate).getPercentage().getText(), "39%", "The percentage is not 39");

        newCompanyModal
                .save();

        CompanyDetailPage companyDetailPage = new CompaniesPage(driver)
                .navigateToTheCompanyDetailPage(model.getName());

        Assert.assertEquals(companyDetailPage.getAssociateNumber(), 2, "The associate number is not correct");
        Assert.assertEquals(companyDetailPage.findAssociateByName(firstAssociate).getPercentage().getText(), "61%", "The percentage is not 61");
        Assert.assertEquals(companyDetailPage.findAssociateByName(secondAssociate).getPercentage().getText(), "39%", "The percentage is not 39");
    }
}
