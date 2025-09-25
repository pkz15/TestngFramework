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
        String reponse=contactus.dropUs();
        Assert.assertEquals(reponse, "Thank you for your inquiry! We will get back to you within 24 hours.");
    }
}
