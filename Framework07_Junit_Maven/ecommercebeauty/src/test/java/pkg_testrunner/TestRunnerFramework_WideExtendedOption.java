package pkg_testrunner;
import com.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import com.github.mkolisnyk.cucumber.runner.ExtendedCucumber;
import com.github.mkolisnyk.cucumber.runner.ExtendedCucumberOptions;
import java.io.File;

@RunWith(ExtendedCucumber.class)
@ExtendedCucumberOptions(
        jsonReport     = "target/cucumber/cucumber.json",
        retryCount     = 2,
        detailedReport = true,
        detailedAggregatedReport = true,
        overviewReport = false,
        toPDF          = true,
        outputFolder   = "target"
)
@CucumberOptions(
    // this is must for rerun.txt to have complete path of failed feature files
    features 	= {"."},
    glue 	 	= {"pkg_stepdefinition","pkg_hooks"},
    plugin 		= {
            "html:target/cucumber",
            "json:target/cucumber/cucumber.json",
            "com.cucumber.listener.ExtentCucumberFormatter:target/html/report.html",
            "rerun:target/rerun.txt"
    },
//    tags        = {"@login"},
    tags        = {"@dummy"},
    dryRun 	 	= false,
    strict 	 	= false,
    monochrome  = true
)

public class TestRunnerFramework_WideExtendedOption {

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



// Retry failed tests
//  With ExtendedCucumberOptions from com.github.mkolisnyk
//  Note that - Extent report captures every rerun and logs the result

