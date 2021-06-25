package com.aircall.es.testscucumber.Utils;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerHandler {
    private static FileHandler fh;
    public static Logger getCustomLogger(Class clazz) {
        Logger logger = Logger.getLogger(clazz.getCanonicalName());

        try {
            // This block configure the logger with handler and formatter
            if(fh == null) {
                fh = new FileHandler("./Report/"+Utils.getName()+"/"+ Utils.executionTimeStamp()+".log");
            }
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        }catch (NullPointerException | IOException e){
            logger.info("Error receiving properties");
        }
        return logger;
    }
}
