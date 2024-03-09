package TestPackage;

import BasePackage.BaseClass;
import PageObjects.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class LogInTest extends BaseClass {


    LoginPage lp;

    public LogInTest() throws IOException {

    }

    @Test
    public void performLogIn() throws IOException {
        lp= new LoginPage(driver);
        lp.performLogin(rc.getProperty("username"),rc.getProperty("Password"));
//        Assert.assertTrue(false);
    }
}
