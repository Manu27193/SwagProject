package BasePackage;

import UtilsPackage.ReadConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseClass {
    public WebDriver driver;
    public ReadConfig rc;

public BaseClass() throws IOException {

}

    @BeforeClass
    @Parameters({"browser"})
    public void setUp(String br, ITestContext context) throws IOException {
        rc=new ReadConfig();
        if(br.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", "./Drivers\\chromedriver.exe");
            driver = new ChromeDriver();
            context.setAttribute("webDriver",this.driver);
        }
        else
            System.out.println("incorrect browser name:" + br);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(rc.getProperty("BaseUrl"));
    }

    public void takeScreenshot(){
        TakesScreenshot ts=(TakesScreenshot)driver;
        File src=ts.getScreenshotAs(OutputType.FILE);
        String dest="C:\\Users\\MANMATH\\IdeaProjects\\org.SwagProject\\SwagProject\\ScreenShots";
        try {
            FileUtils.copyFile(src, new File(dest+"failed"+".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterClass
    public void tearDown(){
    driver.close();
    }

}
