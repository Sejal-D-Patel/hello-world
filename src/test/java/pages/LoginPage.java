package pages;

import Utility.Utilities;
import drivermanager.Constant;
import drivermanager.DriverFactory;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.io.IOException;

public class LoginPage extends DriverFactory {

    @FindBy(name = "name")
    protected WebElement txtName;

    @FindBy(name = "occupation")
    protected WebElement txtOccupation;
    /**
     * verify website login successfully
     *
     * @return
     */
    public LoginPage verifyWebsiteLoginSuccessfully() throws IOException, ParseException {
       getURL();
        enterName();
        enterOccupation();
        return this;
    }

    /**
     * get website url
     * @return
     */

    public LoginPage getURL(){
        driver.get("https://colorlib.com/polygon/gentelella/form_validation.html");
        return this;
    }
    /**
     * Enter name
     * @return
     */
    public LoginPage enterName() throws IOException, ParseException {
        Utilities.getUtilities().waitForVisibilityOfElement(txtName, driver);
        Utilities.getUtilities().sendKey(txtName,Utilities.getUtilities().readDataFromJsonFile(Constant.LoginFile, "name"));
        return this;
    }
    /**
     * Enter Occupation
     * @return
     */
    public LoginPage enterOccupation() throws IOException, ParseException {
        Utilities.getUtilities().waitForVisibilityOfElement(txtOccupation, driver);
        Utilities.getUtilities().sendKey(txtOccupation,Utilities.getUtilities().readDataFromJsonFile(Constant.LoginFile, "occupation"));
        return this;
    }
}
