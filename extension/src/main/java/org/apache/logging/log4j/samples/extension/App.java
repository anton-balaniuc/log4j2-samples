package org.apache.logging.log4j.samples.extension;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {

    private  static Logger logger = LogManager.getLogger(App.class);
    public static void main(String[] args) {
        for (int i = 0; i < 30; i++) {

            logger.info("message " + i);

        }
    }
}
