package stepdefinations;



import org.junit.platform.suite.api.*;

import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@IncludeEngines("cucumber")// default
@SelectClasspathResource("Datatable.feature")// feature file name
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "stepdefinations")// package name
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, html:target/cucumber-reports/login-report.html")
//@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "@smoke or @regression")//report plugin

public class Testrunner {


}
