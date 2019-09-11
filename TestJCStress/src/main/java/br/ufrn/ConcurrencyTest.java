import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.II_Result;

@State
public class ConcurrencyTest extends DataBase {
    @JCStressTest
    @Description("Test description")
    @Outcome(id = "0, 1", expect = Expect.ACCEPTABLE, desc = "get back 0-1")
    @Outcome(id = "1, 0", expect = Expect.ACCEPTABLE, desc = "get back 1-0")
    public static class StressTest1 { //Classe de teste
        @Actor
        public void actor1(ConcurrencyTest myState, II_Result r) {
            //r.r1 = myState.getNext();
            myState.add(new String[]{"1", "2"});
            r.r1 = myState.getLine();
        }

        @Actor
        public void actor2(ConcurrencyTest myState, II_Result r) {
            //r.r2 = myState.getNext();
        }
    }
}