import org.apache.camel.builder.RouteBuilder;

public class GenLoad extends RouteBuilder {
  
  @Override
    public void configure() {
        from("timer:tick")
            .process(ex -> ex.getMessage().setBody(genLoad()))
            .log("Generate Load Camel K! - ${body}");
    }

    private static String genLoad() {
        final int NUM_TESTS = 10;
        long start = System.nanoTime();
        for (int i = 0; i < NUM_TESTS; i++) {
            spin(200);
        }
        return "Took " + (System.nanoTime()-start)/1000000 + "ms (expected " + (NUM_TESTS*500) + ")";
    }

    private static void spin(int milliseconds) {
        long sleepTime = milliseconds*1000000L; // convert to nanoseconds
        long startTime = System.nanoTime();
        while ((System.nanoTime() - startTime) < sleepTime) {}
    }
}
