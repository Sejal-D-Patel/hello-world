package tests;

import drivermanager.DriverFactory;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Reporter.log;

@Listeners({tests.TestListener.class})
@Epic("Regression Test Suite - Login Details")
public class LoginTest extends DriverFactory {
    @Step("Success Message: ")
    public void logToReport(String message) {
        log(message);
    }
    @Test(priority = 0)
    @Severity(SeverityLevel.NORMAL)
    @Step("TestCase_001 - verify Login Application")
    public void test_001_verifyLoginApplication() {
        try {
            loginPage.verifyWebsiteLoginSuccessfully();
          Log.info("verify website loin successfully.");
            logToReport("Login successfully.");
        } catch (AssertionError | Exception E) {
            System.out.println("User could not login to application.");
           Log.error("User could not login to application.");
            Assert.fail("User Could not login to application." + E);
        }
    }
}
