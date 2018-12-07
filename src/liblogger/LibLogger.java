package liblogger;

import java.util.logging.Logger;

public class LibLogger {
    private static final  Logger logger = Logger.getLogger(LibLogger.class.getName());

    private LibLogger(){}

    public static void logMessage (String message){
        logger.info(message);
    }
}
