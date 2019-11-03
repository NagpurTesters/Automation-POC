package POC.Yatra;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(strict = true,
		features = {"src/test/resources/features"},
					tags = {"@Test"},
					glue = { "POC.Yatra.stepdefination" }
	)
	public class RunnerYatra {
		
	}
