package util;

/**
 * Created by marta.ginosyan on 8/26/2016.
 */
public class DBUtil {

    public static String disableForeignKeyChecks = ""
            + "SET FOREIGN_KEY_CHECKS = 0";

    public static String enableForeignKeyChecks = ""
            + "SET FOREIGN_KEY_CHECKS = 1";
}
