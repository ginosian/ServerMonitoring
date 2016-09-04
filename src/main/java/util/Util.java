package util;

import db.DBConnection;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Martha on 9/3/2016.
 */
public class Util {
    private static Properties properties;

    public static String getPropertyValue(String property){
        if(properties == null) getProperty();
        return getProperty().getProperty(property);
    }

    public static Properties getProperty(){
        if (properties != null)return properties;
        properties = new Properties();
        InputStream inputStream = DBConnection.class.getClassLoader().getResourceAsStream("db.properties");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
