package pkg_testrunner;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features 	= {"."},
    glue 	 	= {"pkg_stepdefinition","pkg_hooks"},
    plugin 		= {
            "html:target_profile2/cucumber",
            "json:target_profile2/cucumber/cucumber.json"
    },
    tags        = {"@search"},
    dryRun 	 	= false,
    strict 	 	= false,
    monochrome  = true
)

public class TestRunnerProfile_UAT {

}

