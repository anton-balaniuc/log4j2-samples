import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.FileAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.io.IoBuilder;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SystemTest {
    final static LoggerContext context = (LoggerContext) LogManager.getContext(false);
    final static Configuration configuration = context.getConfiguration();


    @BeforeClass
    public static void before() {
        System.setOut(IoBuilder.forLogger(LogManager.getLogger("system.out")).buildPrintStream());

        System.setErr(IoBuilder.forLogger(LogManager.getLogger("system.err")).buildPrintStream());
    }


    @Test
    public void system_out_file() throws Exception {

        String message = "Lorem ipsum";
        System.out.println(message);

        Path pathTofile = Paths.get(((FileAppender) configuration.getAppender("stdout_to_file")).getFileName());
        Assert.assertTrue("file must have only one message", Files.lines(pathTofile).count() == 1);
        Assert.assertTrue("file must have properMessage", Files.lines(pathTofile).allMatch(l -> l.contains(message)));

    }

    @Test
    public void system_error_file() throws Exception {

        String message = "dolor sit amet";
        System.err.println(message);

        Path pathTofile = Paths.get(((FileAppender) configuration.getAppender("stderr_to_file")).getFileName());
        Assert.assertTrue("file must have only one message", Files.lines(pathTofile).count() == 1);
        Assert.assertTrue("file must have properMessage", Files.lines(pathTofile).allMatch(l -> l.contains(message)));

    }
}
