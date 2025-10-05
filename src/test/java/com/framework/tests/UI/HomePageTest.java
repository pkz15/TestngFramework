package com.framework.tests.UI;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.framework.base.BaseTest;
import com.framework.pages.ServicesPage;

public class HomePageTest extends BaseTest {

	@Test(priority = 1)
    public void NegativeSubscribeValidaion() throws Throwable {
    	
    	ServicesPage Servicespage = new ServicesPage(driver); 
    	String response=Servicespage.ServicesValidation();
        Assert.assertEquals(response, "Collaboration");
    }
	
}
