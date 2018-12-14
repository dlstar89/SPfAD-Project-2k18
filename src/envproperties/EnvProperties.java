package envproperties;
import liblogger.LibLogger;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;



public class EnvProperties {
    private static final String PROP_FILE_NAME = "config.properties";
    private static final Properties PROPS = new Properties();
    private static InputStream inputStream;
    
    private EnvProperties(){}

    private static void loadEnvProperties() throws IOException {
        try {            
            inputStream = EnvProperties.class.getResourceAsStream(PROP_FILE_NAME);
            if (inputStream != null) {
                PROPS.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + PROP_FILE_NAME + "' not found in the classpath");
            }
        } catch (Exception e) {
            LibLogger.logMessageSEVERE(EnvProperties.class, e.toString());
        } finally {
            if(inputStream != null){
                inputStream.close();
            }

        }
    }

    public static String getEnvProperty(String key) throws IOException {
        if (PROPS.isEmpty()) {
            loadEnvProperties();
        }

        if(PROPS.get(key) != null){
            return PROPS.get(key).toString();
        }

        return null;
    }
}
