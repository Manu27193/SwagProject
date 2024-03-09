package UtilsPackage;

import BasePackage.BaseClass;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class Reporting implements ITestListener {

    private static ExtentReports extent = new ExtentReports();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    static {
        ExtentSparkReporter spark = new ExtentSparkReporter("ExtentReports.html");
        extent.attachReporter(spark);
    }
    private WebDriver getDriverFromContext(ITestResult result) {
        ITestContext context = result.getTestContext();
        return (WebDriver) context.getAttribute("WebDriver");
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Start Of Execution(TEST)-> " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("End Of Execution(TEST)-> " + context.getName());
        extent.flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test Started->" + result.getName());
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Pass->" + result.getName());
        test.get().pass("Test passed");
    }

    @SneakyThrows
    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test Failed->" + result.getName());
        test.get().fail(result.getThrowable());
        BaseClass bc= new BaseClass();
        bc.takeScreenshot();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test Skipped->" + result.getName());
        test.get().skip(result.getThrowable());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("Test Failed but within success percentage->" + result.getName());
        test.get().fail("Test failed but it is in defined success ratio " + result.getThrowable());
    }
}
