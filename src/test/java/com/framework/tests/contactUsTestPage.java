package com.framework.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.framework.base.BaseTest;
import com.framework.pages.contactUsPage;

public class contactUsTestPage extends BaseTest {

    @Test
    public void verifyLogin() throws Throwable {
    	
    	contactUsPage contactus = new contactUsPage(driver); // Use driver from BaseTest
    	contactus.clickContactUs();
        contactus.dropUs();
    }
}
