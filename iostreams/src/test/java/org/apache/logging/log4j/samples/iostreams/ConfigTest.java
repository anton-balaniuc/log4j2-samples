package org.apache.logging.log4j.samples.iostreams;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.FileAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.junit.Assert;
import org.junit.Test;

/**
 * Just verify that loggers has proper configuration
 */
public class ConfigTest {

    final static LoggerContext context = (LoggerContext) LogManager.getContext(false);
    final static Configuration configuration = context.getConfiguration();

    static Logger systemOut = (org.apache.logging.log4j.core.Logger) context.getLogger("system.out");
    static Logger systemErr = (org.apache.logging.log4j.core.Logger) context.getLogger("system.err");


    @Test
    public void stdoutAppender_exist_true() {

        Assert.assertNotNull("appender which redirect sout to a file should exist" +
                "exist", configuration.getAppender("stdout_to_file"));
    }

    @Test
    public void stdoutAppender_immediateFlush_true() {
        FileAppender fileAppender = (FileAppender) configuration.getAppender("stdout_to_file");

        Assert.assertTrue("File appender should have immediateFlush true for proper unit testing", fileAppender
                .getImmediateFlush());
    }

    @Test
    public void stdoutAppender_correctFileLocation_true() {
        FileAppender fileAppender = (FileAppender) configuration.getAppender("stdout_to_file");

        Assert.assertEquals("File appender should log to a proper place", "build/logs/sout.log", fileAppender
                .getFileName());
    }

    @Test
    public void stdoutLogger_soutAppender_true() {

        Assert.assertEquals("SystemOutLogger should have only one appender", 1, systemOut.getAppenders().size());

        Assert.assertEquals("SystemOutLogger should have only one stdout_to_file appender", "stdout_to_file",
                systemOut.getAppenders().get("stdout_to_file").getName());
    }

    @Test
    public void stdoutLogger_levelInfo_true() {

        Assert.assertEquals("SystemOutLogger should have level Info", Level.INFO, systemOut.getLevel());
    }

    /// verify that system.err logger and stderr_to_file has proper configuration

    @Test
    public void stdoerrAppender_exist_true() {

        Assert.assertNotNull("File appender which redirects system.err to a file should exist" +
                "exist", configuration.getAppender("stderr_to_file"));
    }

    @Test
    public void stdoerrAppender_immediateFlush_true() {
        FileAppender fileAppender = (FileAppender) configuration.getAppender("stderr_to_file");

        Assert.assertTrue("File appender should have immediateFlush true for proper unit testing", fileAppender
                .getImmediateFlush());
    }

    @Test
    public void stderrAppender_correctFileLocation_true() {
        FileAppender fileAppender = (FileAppender) configuration.getAppender("stderr_to_file");

        Assert.assertEquals("File appender should log to a proper place", "out/logs/serr.log", fileAppender
                .getFileName());
    }

    @Test
    public void stderrLogger_serrAppender_true() {

        Assert.assertEquals("SystemErrLogger should have only one appender", 1, systemErr.getAppenders().size());

        Assert.assertEquals("SystemErrLogger should have only one stderr_to_file appender", "stderr_to_file",
                systemErr.getAppenders().get("stderr_to_file").getName());
    }

    @Test
    public void stderrLogger_levelErr_true() {

        Assert.assertEquals("SystemErrLogger should have level ERROR", Level.ERROR, systemErr.getLevel());
    }
}
