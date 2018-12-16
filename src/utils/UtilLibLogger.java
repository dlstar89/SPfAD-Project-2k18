package utils;

import java.util.logging.Level;
import java.util.logging.Logger;

public class UtilLibLogger {
    private UtilLibLogger() { }

    public static void logMessageSEVERE(Class className, String message) {
        Logger.getLogger(className.getName()).log(Level.SEVERE, message);
    }
    
    public static void logMessageINFO(Class className, String message) {
        Logger.getLogger(className.getName()).log(Level.INFO, message);
    }
}
