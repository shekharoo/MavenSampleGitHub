package SeleniumFiles;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class RunSelenium{

    public static StringBuilder generateRandomStrings()
    {
        //Generate random strings
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        String str = "ABC"+"012"+"ZXC";
        int strLen = str.length();
        for(int i=0;i<strLen;i++)
        {
            int index = (int)((int)strLen*Math.random());
            sb.append(str.charAt(index));
        }
        return sb;
    }
    public static StringBuilder generateRandomMobileNo()
    {
        //Generate random strings
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        String str = "0123456789";
        int strLen = str.length();
        for(int i=0;i<strLen;i++)
        {
            int index = (int)((int)strLen*Math.random());
            sb.append(str.charAt(index));
        }
        return sb;
    }
    public static String extractTextFromPopUp(WebElement ele, String flowName)
    {
        String[] splitString = ele.getText().split(" ");
        String[] String;
        System.out.println(flowName+":"+splitString[1]+" is sucessfully created");
        return splitString[1];
    }

    public static void loginNinza(WebDriver driver) throws InterruptedException, IOException {
        driver.get(ReadFromPropertyFile.readFromPropertyFile("URL"));
        Thread.sleep(1000);
        driver.findElement(By.id("username")).sendKeys(ReadFromPropertyFile.readFromPropertyFile("Username"));
        driver.findElement(By.id("inputPassword")).sendKeys(ReadFromPropertyFile.readFromPropertyFile("Password"));
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        System.out.println("Login is Successfull!!");
    }
    public static void logoutNinZa(WebDriver driver) throws InterruptedException {
        WebElement logOut = driver.findElement(By.xpath("//div[@class='user-icon']"));
        Actions act = new Actions(driver);
        act.moveToElement(logOut).perform();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@class='dropdown-item logout']")).click();
        System.out.println("Log Out is Successfull!!");
    }

    public static void ninzaTC01CreateCampaign(WebDriver driver) throws InterruptedException, IOException {
        //Login to App
        RunSelenium.loginNinza(driver);
        Thread.sleep(2000);
        //Click on Campaign button
        driver.findElement(By.xpath("//div[@id='navbarNav']/ul/li[1]/a")).click();
        //Click on create Campaign button
        driver.findElement(By.xpath("//button[@class='btn btn-info']")).click();
        //Fill Campaign Name
        driver.findElement(By.xpath("//input[@name='campaignName']")).sendKeys(RunSelenium.generateRandomStrings());
        //Fill Target Size
        driver.findElement(By.xpath("//input[@name='targetSize']")).sendKeys(RunSelenium.generateRandomStrings());
        //Click Create Campaign button
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        //Create Campaign pop up
        Thread.sleep(2000);
        WebElement popUp = driver.findElement(By.xpath("//div[@class='Toastify']/div/div/div[1]"));
        //Get text of pop up
        //String camapignName = popUp.getText();
        //System.out.println("Campaign name is: "+camapignName);
        String extractText = RunSelenium.extractTextFromPopUp(popUp,"Campaign");
        WriteToPropertyFile.writeToPropertyFile("Campaign",extractText);
        if(popUp.isDisplayed())
        {
            System.out.println("Create Campaign is Successful!!");
        }
        Thread.sleep(12000);
        //Logout of App
        RunSelenium.logoutNinZa(driver);
        driver.quit();
    }

    public static void ninzaTC02SearchByCampaignID(WebDriver driver) throws InterruptedException, IOException {
        //Login to App
        RunSelenium.loginNinza(driver);
        Thread.sleep(2000);
        //Select Search by Campaign ID
        driver.findElement(By.xpath("//select[@class='form-control']/option[1]")).click();
        //Input Campaign ID
        Thread.sleep(1000);
        //Serach for Camapign id inside search box //Campign ID: CAM00444, Campaign Name: DEMOQSelenium
        driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys("CAM00444");
        //Extract Campaign ID from Table display
        WebElement camID = driver.findElement(By.xpath("//table[@class='table table-striped table-hover']//tbody/tr/td[1]"));
        if(camID.getText().equalsIgnoreCase("CAM00444"))
        {
            System.out.println("Search Campaign by ID is Successful!!");
        }
        Thread.sleep(5000);
        //Logout of App
        RunSelenium.logoutNinZa(driver);
    }

    public static void ninzaTC03SearchByCampaignName(WebDriver driver) throws InterruptedException, IOException {
        //Login to App
        RunSelenium.loginNinza(driver);
        Thread.sleep(2000);
        //Select Search by Campaign ID
        driver.findElement(By.xpath("//select[@class='form-control']/option[2]")).click();
        //Input Campaign ID
        Thread.sleep(1000);
        //Serach for Camapign id inside search box //Campign ID: CAM00444, Campaign Name: DEMOQSelenium
        driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys("DEMOQSelenium");
        //Extract Campaign ID from Table display
        WebElement camID = driver.findElement(By.xpath("//table[@class='table table-striped table-hover']//tbody/tr/td[2]"));
        if(camID.getText().equalsIgnoreCase("DEMOQSelenium"))
        {
            System.out.println("Search Campaign by Name is Successful!!");
        }
        Thread.sleep(5000);
        //Logout of App
        RunSelenium.logoutNinZa(driver);
        //Close browser
        Thread.sleep(2000);
        driver.quit();
    }

    public static void ninzaTC04SearchByContactID(WebDriver driver) throws InterruptedException, IOException {
        //Login to App
        RunSelenium.loginNinza(driver);
        Thread.sleep(2000);
        //Click on Contact header button
        driver.findElement(By.xpath("//div[@id='navbarNav']/ul/li[2]/a")).click();
        Thread.sleep(1000);
        //Select Search by Contact ID
        driver.findElement(By.xpath("//select[@class='form-control']/option[1]")).click();
        //Input Contact ID
        Thread.sleep(1000);
        //Serach for Contact id inside search box //Contact ID: CON00039, Contact Name: QSP
        driver.findElement(By.xpath("//input[@placeholder='Search by Contact Id']")).sendKeys("CON00039");
        //Extract Contact ID from Table display
        WebElement camID = driver.findElement(By.xpath("//table[@class='table table-striped table-hover']//tbody/tr/td[1]"));
        if(camID.getText().equalsIgnoreCase("CON00039"))
        {
            System.out.println("Search Contact by ID is Successful!!");
        }
        Thread.sleep(5000);
        //Logout of App
        RunSelenium.logoutNinZa(driver);
        driver.quit();
    }

    public static void ninzaTC05SearchByContactName(WebDriver driver) throws InterruptedException, IOException {
        //Login to App
        RunSelenium.loginNinza(driver);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@id='navbarNav']/ul/li[2]/a")).click();
        Thread.sleep(1000);
        //Select Search by Contact Name
        driver.findElement(By.xpath("//select[@class='form-control']/option[2]")).click();
        //Input Campaign ID
        Thread.sleep(1000);
        //Serach for Contact name inside search box //Contact ID: CAM00444, Contac Name: QSP
        driver.findElement(By.xpath("//input[@placeholder='Search by Contact Name']")).sendKeys("QSP");
        //Extract Contact Name from Table display
        WebElement camID = driver.findElement(By.xpath("//table[@class='table table-striped table-hover']//tbody/tr/td[2]"));
        if(camID.getText().equalsIgnoreCase("QSP"))
        {
            System.out.println("Search Contact by Name is Successful!!");
        }
        Thread.sleep(5000);
        //Logout of App
        RunSelenium.logoutNinZa(driver);
        //Close browser
        Thread.sleep(2000);
        driver.quit();
    }

    public static void ninzaTC06CreateLeadUsingCampaignID(WebDriver driver) throws InterruptedException, IOException {
        //Login to App
        RunSelenium.loginNinza(driver);
        Thread.sleep(2000);
        //Click on Lead button
        driver.findElement(By.xpath("//div[@id='navbarNav']/ul/li[3]/a")).click();
        Thread.sleep(1000);
        //Click on create Lead button
        driver.findElement(By.xpath("//button[@class='btn btn-info']")).click();
        Thread.sleep(1000);
        //Store parent window handle
        String parentWh = driver.getWindowHandle();
        //Fill Lead Name
        driver.findElement(By.xpath("//input[@name='name']")).sendKeys(RunSelenium.generateRandomStrings());
        Thread.sleep(1000);
        //Fill Company
        driver.findElement(By.xpath("//input[@name='company']")).sendKeys(RunSelenium.generateRandomStrings());
        Thread.sleep(1000);
        //Fill Lead Source
        driver.findElement(By.xpath("//input[@name='leadSource']")).sendKeys(RunSelenium.generateRandomStrings());
        Thread.sleep(1000);
        //Fill Industry
        driver.findElement(By.xpath("//input[@name='industry']")).sendKeys(RunSelenium.generateRandomStrings());
        Thread.sleep(1000);
        //Fill Phone
        driver.findElement(By.xpath("//input[@name='phone']")).sendKeys(RunSelenium.generateRandomMobileNo());
        Thread.sleep(1000);
        //Fill Lead Status
        driver.findElement(By.xpath("//input[@name='leadStatus']")).sendKeys(RunSelenium.generateRandomStrings());
        Thread.sleep(1000);
        //Campaign button
        driver.findElement(By.xpath("//div[@class='form-container']/div[2]/div[9]//button")).click();
        Thread.sleep(1000);
        //Passing window handle to dialogue box
        Set<String> windowHndleSet = driver.getWindowHandles();
        System.out.println("Window handle size: "+windowHndleSet.size());
        //Switch window handle to Dialogue box
        for(String whs:windowHndleSet)
        {
            driver.switchTo().window(whs);
            if(driver.getTitle().equalsIgnoreCase("Select Campaign"))
            {
                System.out.println("Switch to Dialogue box window successful");
                //windowHndleSet.remove(whs);
                //driver.switchTo().window(whs);
            }
        }
        Thread.sleep(1000);
        //select campaign id from drop down
        driver.findElement(By.xpath("//select[@id='search-criteria']/option[1]"));
        //Serach for Camapign id inside search box //Campign ID: CAM00444, Campaign Name: DEMOQSelenium
        driver.findElement(By.xpath("//input[@id='search-input']")).sendKeys("CAM00444");
        Thread.sleep(1000);
        //Click on Select Campaign button
        driver.findElement(By.xpath("//button[@class='select-btn']")).click();
        Thread.sleep(1000);
        //Switch back to parent window handle
        System.out.println("Switching to parent window");

        driver.switchTo().window(parentWh);
        Thread.sleep(2000);
        System.out.println("Switch to parent window is successful!!");
        //Click on Create Contact button
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(1000);
        //Capture successful create contact pop up
        WebElement popUp = driver.findElement(By.xpath("//div[@class='Toastify']/div/div/div"));
        if(popUp.isDisplayed())
        {
            System.out.println("Create Lead is Successful!!");
        }
        Thread.sleep(10000);
        //Logout of App
        RunSelenium.logoutNinZa(driver);
        driver.quit();
    }

    public static void ninzaTC07SearchByLeadID(WebDriver driver) throws InterruptedException, IOException {
        //Login to App
        RunSelenium.loginNinza(driver);
        Thread.sleep(2000);
        //Click on Lead header button
        driver.findElement(By.xpath("//div[@id='navbarNav']/ul/li[3]/a")).click();
        Thread.sleep(1000);
        //Select Search by Lead ID
        driver.findElement(By.xpath("//select[@class='form-control']/option[1]")).click();
        //Input Lead ID
        Thread.sleep(1000);
        //Serach for Lead id inside search box //Lead ID: LEAD00032, Lead Name: QspidersSelenium
        driver.findElement(By.xpath("//input[@placeholder='Search by Lead Id']")).sendKeys("LEAD00032");
        //Extract Lead ID from Table display
        WebElement camID = driver.findElement(By.xpath("//table[@class='table table-striped table-hover']//tbody/tr/td[1]"));
        if(camID.getText().equalsIgnoreCase("LEAD00032"))
        {
            System.out.println("Search Lead by ID is Successful!!");
        }
        Thread.sleep(5000);
        //Logout of App
        RunSelenium.logoutNinZa(driver);
        driver.quit();
    }

    public static void ninzaTC08SearchByLeadName(WebDriver driver) throws InterruptedException, IOException {
        //Login to App
        RunSelenium.loginNinza(driver);
        Thread.sleep(2000);
        //Click on Lead header button
        driver.findElement(By.xpath("//div[@id='navbarNav']/ul/li[3]/a")).click();
        Thread.sleep(1000);
        //Select Search by Lead Name
        driver.findElement(By.xpath("//select[@class='form-control']/option[2]")).click();
        //Input Lead Name
        Thread.sleep(1000);
        //Serach for Lead name inside search box //Lead ID: LEAD00032, Lead Name: QspidersSelenium
        driver.findElement(By.xpath("//input[@placeholder='Search by Lead Name']")).sendKeys("QspidersSelenium");
        //Extract Lead Name from Table display
        WebElement camID = driver.findElement(By.xpath("//table[@class='table table-striped table-hover']//tbody/tr/td[2]"));
        if(camID.getText().equalsIgnoreCase("QspidersSelenium"))
        {
            System.out.println("Search Lead by Name is Successful!!");
        }
        Thread.sleep(5000);
        //Logout of App
        RunSelenium.logoutNinZa(driver);
        //Close browser
        Thread.sleep(2000);
        driver.quit();
    }


    public static void ninzaTC10CreateContactUsingCampaignID(WebDriver driver) throws InterruptedException, IOException {
        //Login to App
        RunSelenium.loginNinza(driver);
        Thread.sleep(2000);
        //Click on Contact button
        driver.findElement(By.xpath("//div[@id='navbarNav']/ul/li[2]/a")).click();
        Thread.sleep(1000);
        //Click on create Contact button
        driver.findElement(By.xpath("//button[@class='btn btn-info']")).click();
        Thread.sleep(1000);
        //Store parent window handle
        String parentWh = driver.getWindowHandle();
        //Fill Organization
        driver.findElement(By.xpath("//input[@name='organizationName']")).sendKeys(RunSelenium.generateRandomStrings());
        Thread.sleep(1000);
        //Fill Title
        driver.findElement(By.xpath("//input[@name='title']")).sendKeys(RunSelenium.generateRandomStrings());
        Thread.sleep(1000);
        //Fill Contact Name
        driver.findElement(By.xpath("//input[@name='contactName']")).sendKeys(RunSelenium.generateRandomStrings());
        Thread.sleep(1000);
        //Fill Mobile
        driver.findElement(By.xpath("//input[@name='mobile']")).sendKeys(RunSelenium.generateRandomMobileNo());
        Thread.sleep(1000);
        //Campaign button
        driver.findElement(By.xpath("//div[@class='form-container']/div[2]/div[4]//button")).click();
        Thread.sleep(1000);
        //Passing window handle to dialogue box
        Set<String> windowHndleSet = driver.getWindowHandles();
        System.out.println("Window handle size: "+windowHndleSet.size());
        //Switch window handle to Dialogue box
        for(String whs:windowHndleSet)
        {
            driver.switchTo().window(whs);
            if(driver.getTitle().equalsIgnoreCase("Select Campaign"))
            {
                System.out.println("Switch to Dialogue box window successful");
                //windowHndleSet.remove(whs);
                //driver.switchTo().window(whs);
            }
        }
        Thread.sleep(1000);
        //select campaign id from drop down
        driver.findElement(By.xpath("//select[@id='search-criteria']/option[1]"));
        //Serach for Camapign id inside search box //Campign ID: CAM00444, Campaign Name: DEMOQSelenium
        driver.findElement(By.xpath("//input[@id='search-input']")).sendKeys("CAM00444");
        Thread.sleep(1000);
        //Click on Select Campaign button
        driver.findElement(By.xpath("//button[@class='select-btn']")).click();
        Thread.sleep(1000);
        //Switch back to parent window handle
        System.out.println("Switching to parent window");

        driver.switchTo().window(parentWh);
        Thread.sleep(2000);
        System.out.println("Switch to parent window is successful!!");
        //Click on Create Contact button
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(1000);
        //Capture successful create contact pop up
        WebElement popUp = driver.findElement(By.xpath("//div[@class='Toastify']/div/div/div"));
        if(popUp.isDisplayed())
        {
            System.out.println("Create Contact is Successful!!");
        }
        Thread.sleep(10000);
        //Logout of App
        RunSelenium.logoutNinZa(driver);
        driver.quit();
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        // Create ChromeOptions object
        ChromeOptions options = new ChromeOptions();

        // Create a HashMap to store user profile preferences
        Map<String, Object> prefs = new HashMap<>();

        // Disable the "Change your password" pop-up related to password leak detection
        prefs.put("profile.password_manager_leak_detection", false);

        // Optionally, disable saving passwords and password manager entirely
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);

        // Apply the preferences to ChromeOptions
        options.setExperimentalOption("prefs", prefs);
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        RunSelenium.ninzaTC01CreateCampaign(driver);
        //RunSelenium.ninzaTC02SearchByCampaignID(driver);
        //RunSelenium.ninzaTC03SearchByCampaignName(driver);
        //RunSelenium.ninzaTC04SearchByContactID(driver);
        //RunSelenium.ninzaTC05SearchByContactName(driver);
        //RunSelenium.ninzaTC06CreateLeadUsingCampaignID(driver);
        //RunSelenium.ninzaTC07SearchByLeadID(driver);
        //RunSelenium.ninzaTC08SearchByLeadName(driver);
        //RunSelenium.ninzaTC10CreateContactUsingCampaignID(driver);

    }
}

