package ru.selenium.util;

import java.io.*;
import java.util.Properties;

/*
 * Class that extracts properties from the prop file.
 * 
 * @author Sebastiano Armeli-Battana
 */
public class PropertyLoader {

    private static final String PROP_FILE = "/application.properties";

    public static String loadProperty(String name) {
        Properties props = new Properties();
        try {
            props.load(PropertyLoader.class.getResourceAsStream(PROP_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String value = "";

        if (name != null) {
            value = props.getProperty(name);
        }
        return value;
    }

    public static String loadProperty(String file, String name) {
        Properties props = new Properties();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new
                    InputStreamReader(PropertyLoader.class.getResourceAsStream(file),
                    "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        InputStream input =  Thread . currentThread (). getContextClassLoader (). getResourceAsStream ( file );
        try {
            props.load(br);

        } catch (IOException e) {
            e.printStackTrace();
        }


        String value = "";

        if (name != null) {
            value = props.getProperty(name);
        }
        return value;
    }
}