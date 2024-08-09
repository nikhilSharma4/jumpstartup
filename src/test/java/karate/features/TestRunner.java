package karate.features;
import com.intuit.karate.junit5.Karate;


public class TestRunner {
    @Karate.Test
    Karate testFullPath() {
        System.out.println("harsh here it is");
        return Karate.run("freelancer").relativeTo(getClass());
    }
}
