package base;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features="features",
        glue="classpath:steps",
        plugin={"pretty", "html:target/cucumber-html-report"}
        )
public class RunCucumberTest {
}