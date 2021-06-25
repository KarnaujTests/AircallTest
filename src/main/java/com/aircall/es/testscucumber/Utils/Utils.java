package com.aircall.es.testscucumber.Utils;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Utils {

    private static int cont = 1;
    private static String name = "";
    private static WebDriver driverCalling;
    private static WebDriver driverReceiver;
    private static Scenario scenario;

    public static String replaceFirstPlaceholder(String originalString, String replacement) {
        int indexReplaceTextIni = originalString.indexOf("#!");
        int indexReplaceTextEnd = originalString.indexOf("#", indexReplaceTextIni + 1) + 1;

        if (indexReplaceTextIni == -1 || indexReplaceTextEnd == 0) {
            Assert.fail("The parametric identifier should be indicated by inserting an element starting by" +
                    "#! and ending in # in the webelement's xpath to be able to replace it in execution time.");
        }

        return originalString.replace(originalString.substring(indexReplaceTextIni, indexReplaceTextEnd),
                replacement);
    }


//    @Before
    public static WebDriver initDriver() {

        System.setProperty("webdriver.chrome.driver", PropertiesHandler.getProgramProperties().getProperty("chromedriver"));
        ChromeOptions options = new ChromeOptions();
        options.addArguments("incognito");
        options.addArguments("use-fake-device-for-media-stream");
        options.addArguments("use-fake-ui-for-media-stream");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setBrowserName("chrome");
        capabilities.setAcceptInsecureCerts(true);
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        options.merge(capabilities);
        return new ChromeDriver(options);
    }

    public static String logException(Exception e) {
        StringWriter sWriter = new StringWriter();
        PrintWriter pWriter = new PrintWriter(sWriter);
        e.printStackTrace(pWriter);
        return sWriter.toString();
    }

    public static void screenShot(String name, WebDriver driver) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File("./Report/" + name + "/" + executionTimeStamp() + " Step " + cont + ".png"));
            cont++;
        } catch (Exception e) { }
    }

    public static void resize(String inputImagePath,
                              String outputImagePath, double percent) throws IOException {
        File inputFile = new File(inputImagePath);
        BufferedImage inputImage = ImageIO.read(inputFile);
        int scaledWidth = (int) (inputImage.getWidth() * percent);
        int scaledHeight = (int) (inputImage.getHeight() * percent);
        resize(inputImagePath, outputImagePath, scaledWidth, scaledHeight);
    }

    public static void resize(String inputImagePath,
                              String outputImagePath, int scaledWidth, int scaledHeight)
            throws IOException {
        // reads input image
        File inputFile = new File(inputImagePath);
        BufferedImage inputImage = ImageIO.read(inputFile);

        // creates output image
        BufferedImage outputImage = new BufferedImage(scaledWidth,
                scaledHeight, inputImage.getType());

        // scales the input image to the output image
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();

        // extracts extension of output file
        String formatName = outputImagePath.substring(outputImagePath
                .lastIndexOf(".") + 1);

        // writes to output file
        ImageIO.write(outputImage, formatName, new File(outputImagePath));
    }

    public static void embedResult(Scenario scenario, WebDriver driver) {
        try {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Report");
        } catch (Exception e) {

        }
    }

    public static String executionTimeStamp() {
        return new SimpleDateFormat("HH.mm.ss.SSS").format(new Date());
    }

    public static WebDriver getCallingDriver() {
        if (driverCalling == null) {
            driverCalling = initDriver();
            driverCalling.manage().window().maximize();
            driverCalling.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
        return driverCalling;
    }
    public static WebDriver getReceiverDriver() {
        if (driverReceiver == null) {
            driverReceiver = initDriver();
            driverReceiver.manage().window().maximize();
            driverReceiver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
        return driverReceiver;
    }

    public static long timeOut() {
        return Long.parseLong(PropertiesHandler.getProgramProperties().getProperty("timeout"));
    }

    public static void setName(String name) {
        name = name;
    }
    public static String getName() {
        return name;
    }
    public static Scenario getScenario() {
        return scenario;
    }

    @AfterStep
    public void after(Scenario scenario) {
        this.name = scenario.getName();
        this.scenario = scenario;
        if (this.driverReceiver != null) {
            Utils.screenShot(name + " in Receiver", this.driverReceiver);
            embedResult(scenario, this.driverReceiver);
        }
        if (this.driverCalling != null) {
            Utils.screenShot(name + " in Receiver", this.driverCalling);
            embedResult(scenario, this.driverCalling);
        }
    }

    @After
    public void quitDriver() {
        if (driverCalling != null)
            this.driverCalling.quit();
        if (driverReceiver != null)
            this.driverReceiver.quit();
    }

    public static String simplifyPhone(String extendedPhone) {
        return extendedPhone.replace(" ", "").replace("+","");
    }

}
