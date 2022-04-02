package pkg_testrunner;
import com.github.mkolisnyk.cucumber.runner.ExtendedCucumber;
import com.github.mkolisnyk.cucumber.runner.ExtendedCucumberOptions;
import cucumber.api.CucumberOptions;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import pkg_utility.Utilities_General;
import pkg_utility.Utility_Filehandler;

@RunWith(ExtendedCucumber.class)
@ExtendedCucumberOptions(
        jsonReport     = "target/cucumber/cucumber.json",
        retryCount     = 1,
        detailedReport = true,
        detailedAggregatedReport = true,
        overviewReport = false,
        toPDF          = true,
        outputFolder   = "target"
)
@CucumberOptions(
    features 	= {"."},
    glue 	 	= {"pkg_stepdefinition","pkg_hooks"},
    plugin 		= {
            "html:target/cucumber",
            "json:target/cucumber/cucumber.json",
            "com.cucumber.listener.ExtentCucumberFormatter:target/html/report.html",
            "rerun:target/rerun.txt"
    },
    tags        = {"@dummy"},
//    tags        = {"@register"},
    dryRun 	 	= false,
    strict 	 	= false,
    monochrome  = false
)

public class TestRunnerFramework_WideExtendedOption {

    @Before
    public void setupBeforeTest(){
        // empty
    }
    @After
    public void tearDownBeforeTest(){
        // empty
    }

    @BeforeClass
    public static void setupBeforeClass() {
        //GlobalInit
        new Utilities_General().GetCommandLineParam();

        new Utility_Filehandler().ExcelDataReaderInit();

        new Utility_Filehandler().PropertiesDataReaderInit();

        new Utility_Filehandler().CsvDataReaderInit();

        new Utilities_General().DbConnectionInit();

        // Making required environment web-app up and running - optional step
        // Launching web browser - To be done before cucumber every scenario
    }

    @AfterClass
    public static void teardownAfterClass() {
        //GlobalTearDown
        // Shutting down web browser - To be done after cucumber every scenario
        // Making required environment web-app down - optional step

        new Utilities_General().DbConnectionClose();

        new Utility_Filehandler().ExcelDataReaderClose();

        new Utilities_General().GenerateReport();

        // Extra step for JVM shutdown
        Runtime runtime = Runtime.getRuntime();
        // Java shutdown hook are handy to run some code when program exit
        // e.g. clean up resources, send reports etc
        runtime.addShutdownHook(new Thread(){
              public void run(){
                  try {new Utilities_General().SendReportViaEmail();}
                  catch (Exception e) { e.printStackTrace();}
              }
          }
        );
        try{Thread.sleep(5000);}catch (Exception e){}
    }


}


//  Logging using org.apache.log4j (others are 1) SLF4J, 2) Built in java.util.logging package)
//      Why not SYSOUT? - to have a permanent place of logs stored for further analysis
//                      - also useful in multi threaded application logging
//      need this file at specific location src/test/resources/log4j.properties
//      log level     debug < info < warn < error < fatal
//
//  Full set of steps to be done BeforeClass/ AfterClass
//
//  Sending HTML test report using email
//      As attachment
