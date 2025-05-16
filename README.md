# Hahn-project
This project contains automated tests using Selenium WebDriver, TestNG, and Maven, targeting critical user flows for the Magento demo site https://magento.softwaretestingboard.com.
Below is a step-by-step guide to setting up and running your automated tests locally.

**Prerequisites**
    Java 1.8+ (or your version)
    Maven (latest)
    Selenium WebDriver 4.x
    TestNG
    ChromeDriver
    
**Critical User Flows Covered**
    User Login (positive & negative)
    Add to Cart
    Registration

**Install Prerequisites**
    Java JDK 1.8+
    Maven
    Chrome browser
        - Download and place ChromeDriver in your system PATH: https://chromedriver.chromium.org/downloads
        
**How to Set Up & Run the Tests**
  1. Clone the Repository
    git clone https://github.com/Abduben/Hahn-project.git
    cd your-repo-name
 
  2. Install Dependencies
    mvn clean install

  4. Run the Tests
    mvn test  or  mvn test -DsuiteXmlFile=testng.xml
