/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package envproperties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author dstarcenko
 */
public class EnvProperties {
    private static final String PROP_FILE_NAME = "config.properties";
    private static final Properties PROPS = new Properties();
    private static InputStream inputStream;

    private static void loadEnvProperties() throws IOException {
        try {            
            inputStream = EnvProperties.class.getResourceAsStream(PROP_FILE_NAME);
            if (inputStream != null) {
                PROPS.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + PROP_FILE_NAME + "' not found in the classpath");
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
    }

    public static String getEnvProperty(String _key) throws IOException {
        if (PROPS.isEmpty() == true) {
            loadEnvProperties();
        }

        String property = PROPS.get(_key).toString();

        return property;
    }
}
