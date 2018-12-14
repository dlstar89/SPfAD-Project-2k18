package liblogger;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LibLogger {
    private LibLogger() { }

    public static void logMessageSEVERE(Class className, String message) {
        Logger.getLogger(className.getName()).log(Level.SEVERE, message);
    }
}
