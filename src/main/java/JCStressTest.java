import org.openjdk.jcstress.annotations.Description;
import org.openjdk.jcstress.annotations.Outcome;
import org.openjdk.jcstress.annotations.State;

public class JCStressTest {
    @State
    public static class MainState extends Main{}

    //@JCStressTest
    @Description("Test main")
    //@Outcome(id = "")
    public static class StressTest1 {

    }
}
