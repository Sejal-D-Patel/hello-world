package Utility;

import drivermanager.DriverFactory;

import org.apache.commons.io.FileUtils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Utilities extends DriverFactory {
    private static String screenshotName;
    private static final int explicitWaitDefault = 180;
    private static Utilities instance = null;
    public static synchronized Utilities getUtilities() {
        if (instance == null) {
            instance = new Utilities();
        }
        return instance;
    }


    /**
     * get Datetime
     * @return
     */
    public String getDateTime() {

        // Create object of SimpleDateFormat class and decide the format
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

        // get current date time with Date()
        Date date = new Date();

        // Now format the date
        String currentDate = dateFormat.format(date);

        String newCurrentDate = currentDate.replace('/', '-');
        return newCurrentDate;

    }

    /**
     * Click on element
     * @param element
     */
    public void clickOnElement(WebElement element) {
        element.click();
    }

    /**
     * Send key
     * @param element
     * @param value
     */
    public void sendKey(WebElement element, String value){
        element.clear();
        element.sendKeys(value);
    }

    /**
     * Wait for element visible
     * @param webElement
     * @param driver
     * @return
     */
    public boolean waitForVisibilityOfElement(WebElement webElement, WebDriver driver) {
        long timeOutSecond = 60;
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOutSecond);
            wait.until(ExpectedConditions.visibilityOf(webElement));
            return true;
        } catch (Exception e) {
            System.out.println("Can not wait till element visible");
            return false;
        }
    }

    /**
     * Wait for element click
     * @param element
     * @param driver
     */
    public void waitForElementTobeClickable(final WebElement element, WebDriver driver) {
        try {
            new WebDriverWait(driver, explicitWaitDefault)
                    .until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            System.out.println("Can not wait till element click");
        }
    }

    /**
     * select value from dropdown/selectBox by value
     *
     * @param element
     * @param text
     */
    public void selectFromDropDownByVisibleText(WebElement element, String text) {
        try {
            Select select = new Select(element);
            select.selectByVisibleText(text);
            System.out.println("Successfully select the following keys: " + text + ", to the following WebElement: " + "<" + element.toString() + ">");
        } catch (Exception e) {
            System.out.println("Unable to select the following keys: " + text + ", to the following WebElement: " + "<" + element.toString() + ">");
            Assert.fail("Unable to select the required index from the dropdown menu, Exception: " + e.getMessage());
        }
    }

    /**
     * return date stamp
     * @param fileExtension
     * @return
     */
    public static String returnDateStamp(String fileExtension) {
        Date d = new Date();
        String date = d.toString().replace(":", "_").replace(" ", "_") + fileExtension;
        return date;
    }

    /**
     *
     * @throws IOException
     * @throws InterruptedException
     */
    public static void captureScreenshot(WebDriver driver) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        screenshotName = returnDateStamp(".jpg");
        System.out.println(screenshotName);
        try {
            FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir") + "/screenSort/" + screenshotName));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * File Upload
     *
     * @param element
     * @param filePath
     */
    public void uploadFile(WebElement element, String filePath) {
        try {
            element.sendKeys(filePath);
            System.out.println("Successfully upload a file: " + "<" + element.toString() + ">");
        } catch (Exception e) {
            System.out.println("Unable to upload a file : " + "<" + element.toString() + ">");
            Assert.fail("Unable to upload a file, Exception: " + e.getMessage());
        }
    }
    /**
     * Read data from Json File.
     *
     * @param fileName
     * @param data
     * @return
     * @throws IOException
     * @throws ParseException
     */
    public static String readDataFromJsonFile(String fileName, String data) throws IOException, ParseException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(fileName));
        JSONObject jsonObject = (JSONObject) obj;
        return (String) jsonObject.get(data);
    }


}
