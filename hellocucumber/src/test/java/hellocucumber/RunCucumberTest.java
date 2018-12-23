package hellocucumber;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
/**
 * 1. Pom.xml - include, cucumber dependencies, with selenium java
 * 2. create feature-files under resources directory. Also, include the tag name in the runner class
 * 3. In the steps class, create webdriver (make sure to set system property for driver exe)
 * @author btamilselvan
 *
 */
@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:target/cucumber"}, glue="stepdefs",  tags={"@test"})
public class RunCucumberTest {
}