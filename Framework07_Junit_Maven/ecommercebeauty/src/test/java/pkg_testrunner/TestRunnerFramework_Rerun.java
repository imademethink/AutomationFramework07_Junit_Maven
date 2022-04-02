package pkg_testrunner;
import com.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.io.File;

@RunWith(Cucumber.class)
@CucumberOptions(
    features 	= {"@target/rerun.txt"},
    glue 	 	= {"pkg_stepdefinition","pkg_hooks"},
    plugin 		= {
            "html:target2/cucumber",
            "json:target2/cucumber/cucumber.json",
            "com.cucumber.listener.ExtentCucumberFormatter:target2/html/report.html"
    }
)

public class TestRunnerFramework_Rerun {

    @BeforeClass
    public static void setupBeforeClass() {
        // empty
    }

    @AfterClass
    public static void setup() {
        System.out.println("Generating Extent Report");
        Reporter.loadXMLConfig(new File(System.getProperty("user.dir") + "/src/test/resources/extent-config.xml"));
        Reporter.setSystemInfo("Test User", "Tony Stark");
        Reporter.setSystemInfo("Operating System Type", "Windows");
        Reporter.setSystemInfo("Build version", "v 1.2.3");
        Reporter.setTestRunnerOutput("Extent Report for Regression");
    }

    @Before
    public void setupBeforeTest(){
        // empty
    }
    @After
    public void tearDownBeforeTest(){
        // empty
    }

}



