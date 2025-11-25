package com.example.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtils {
    public static Logger getLogger(Class cls) {
        return LogManager.getLogger(cls);
    }
}
