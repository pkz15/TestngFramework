package com.framework.tests.UI;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.framework.base.BaseTest;
import com.framework.pages.contactUsPage;

public class ContactusTest extends BaseTest {
	 
	@Test(priority = 3)
	public void FormSubmission() throws Throwable {
		
    	contactUsPage contactus = new contactUsPage(driver); 
    	contactus.clickContactUs();
    	String projectPath = System.getProperty("user.dir");
    	String filePath = projectPath + "/src/main/resources/1000_F_634676819_b1bxBejIyThDWBIkga2w3Cl0gTyyOSPL.jpg";
        String reponse=contactus.dropUs(filePath);
        Assert.assertEquals(reponse, "Thank you for your inquiry! We will get back to you within 24 hours.");
    }
}
