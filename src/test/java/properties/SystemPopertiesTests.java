package tests.properties;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class SystemPropertiesTests {
    @Test
    void simplePropertyTest() {
        String browserName = System.getProperty("browser");
        System.out.println(browserName); // null
    }

    @Test
    void simpleProperty1Test() {
        System.setProperty("browser", "opera");
        String browserName = System.getProperty("browser");
        System.out.println(browserName); // opera
    }

    @Test
    void simpleProperty2Test() {
        String browserName = System.getProperty("browser", "firefox");
        System.out.println(browserName); // firefox
    }

    @Test
    void simpleProperty3Test() {
        System.setProperty("browser", "opera");
        String browserName = System.getProperty("browser", "firefox");
        System.out.println(browserName); // opera
    }

    @Test
    @Tag("one_property_test")
    void simpleProperty4Test() {
        String browserName = System.getProperty("browser", "firefox");
        System.out.println(browserName);
        // gradle clean one_property -Dbrowser=safari
        // safari
    }

    @Test
    @Tag("many_properties_test")
    void simpleProperty5Test() {
        String browserName = System.getProperty("browser", "firefox");
        String browserVersion = System.getProperty("browser_version", "105");
        String browserSize = System.getProperty("browser_size", "1920x1080");

        System.out.println(browserName); // opera
        System.out.println(browserVersion); // opera
        System.out.println(browserSize); // opera

        /*
        From IDEA
                firefox
                105
                1920x1080
        gradle clean many_properties
                firefox
                105
                1920x1080
        gradle clean many_properties -Dbrowser=safari
                safari
                105
                1920x1080
        gradle clean many_properties -Dbrowser=safari -Dbrowser_version=99.0 -Dbrowser_size=300x300
                safari
                99.0
                300x300
         */
    }

    @Test
    @Tag("hello")
    void simpleProperty6Test() {
        System.out.println("Hello, " + System.getProperty("user_name", "unknown student"));

        /*
        gradle clean hello -Duser_name=Alex
                Hello, Alex
        gradle clean hello -Duser_name=Alex Egorov
                FAILURE: Build failed with an exception.
                * What went wrong:
                Task 'Egorov' not found in root project 'qa_guru_15_jenkins'.
        gradle clean hello "-Duser_name=Alex Egorov"
        gradle clean hello -Duser_name="Alex Egorov"
                Hello, Alex Egorov
         */
    }
}