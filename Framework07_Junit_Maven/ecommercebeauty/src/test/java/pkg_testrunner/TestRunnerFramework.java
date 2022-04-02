package pkg_testrunner;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import com.cucumber.listener.Reporter;
import java.io.File;

@RunWith(Cucumber.class)
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
    tags        = {"@login"},
//    tags        = {"@dummy"},
    dryRun 	 	= false,
    strict 	 	= false,
    monochrome  = true
)

public class TestRunnerFramework {

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
//      with rerun plugin + multiple runner files in maven-surefire-plugin
//          need to change target folders for each runner files
//      With ExtendedCucumberOptions from com.github.mkolisnyk
//          Retry option
//          PDF reporting
//      with maven-surefire-plugin version 2.18 onwards
//          command line option             mvn clean test -DrerunFailingTestsCount=2
//          in pom.xml configuration        <rerunFailingTestsCount>2</rerunFailingTestsCount>
// Maven profiles
//      Note the id tag in pom_2.xml
//      To run specific profile use -P <prifile_name> option e.g.
//          mvn clean test -P UAT_PROFILE
//      In current example use         mvn clean test -f pom_2.xml -P UAT_PROFILE
//      Checkout the report in target_profile2 folder



// Page Object
// Page factory
//

