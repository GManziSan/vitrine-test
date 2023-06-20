package vitrine.runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber-report.html"},
        //dryRun = true,
        monochrome = true,
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        glue = "vitrine.steps",
        features = "src/test/resources/features/Vitrine.feature"
)
public class RunVitrineTest {
}
