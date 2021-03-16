package tests;

import drivermanager.DriverFactory;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class TestListener extends DriverFactory implements ITestListener {
    public static String x;

    private static String GetTestMethodName(ITestResult iTestResult)
    {
        return iTestResult.getMethod().getConstructorOrMethod().getName();

    }

    @Attachment(value = "Page Screenshot", type = "image/png")
    public static byte[] saveScreenshotPNG(WebDriver driver)
    {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("Method" + GetTestMethodName(iTestResult) + "Pass");
        Object testClass = iTestResult.getInstance();
        WebDriver driver = ((DriverFactory) testClass).driver;
        if(driver instanceof WebDriver)
        {

            System.out.println("Screen captured for test case : " + GetTestMethodName(iTestResult) + "Pass");
            saveScreenshotPNG(driver);

        }
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("Method" + GetTestMethodName(iTestResult) + "Fail");
        Object testClass = iTestResult.getInstance();
        WebDriver driver = ((DriverFactory) testClass).driver;
        if(driver instanceof WebDriver)
        {

            System.out.println("Screen captured for test case : " + GetTestMethodName(iTestResult) + "Pass");
            saveScreenshotPNG(driver);

        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }
}
