package util;

import db.DBConnection;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

/**
 * Created by Martha on 9/3/2016.
 */
public class Util {
    private static Properties properties;
    private static Random rand = new Random();


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



    public static int rand(int lowest, int highest){
        return rand.nextInt(highest) + lowest;
    }



    public static Map<String, String> mapFromRequestStream(HttpServletRequest req) throws IOException {
        return makeQueryMap(decodeStringFromRequestStream(req));
    }
    public static String decodeStringFromRequestStream(HttpServletRequest req) throws IOException {
        InputStream is = req.getInputStream();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] buf = new byte[32];
        int r = 0;
        while(r >= 0 ) {
            r = is.read(buf);
            if(r >= 0) os.write(buf, 0, r);
        }
        String s = new String(os.toByteArray(), "UTF-8");
        return URLDecoder.decode(s, "UTF-8");
    }
    private static Map<String, String> makeQueryMap(String query) throws UnsupportedEncodingException {
        String[] params = query.split("&");
        Map<String, String> map = new HashMap<String, String>();
        for( String param : params ) {
            String[] split = param.split("=");
            map.put(URLDecoder.decode(split[0], "UTF-8"), URLDecoder.decode(split[1], "UTF-8"));
        }
        return map;
    }
}
