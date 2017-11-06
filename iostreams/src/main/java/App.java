import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.io.IoBuilder;

public class App {
    public static void main(String[] args) {

        System.out.println("This message should appear on console");
        System.setOut(IoBuilder.forLogger(LogManager.getLogger("system.out")).buildPrintStream());
        System.out.println("But this message should be redirected to a file");

        System.err.println("This error should appear on console");
        System.setErr(IoBuilder.forLogger(LogManager.getLogger("system.err")).buildPrintStream());
        System.err.println("But this error should be redirected to a file");
    }
}