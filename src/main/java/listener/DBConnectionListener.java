package listener;

import java.sql.Connection;

/**
 * Created by marta.ginosyan on 8/26/2016.
 */
public interface DBConnectionListener {
    Connection unMapConnectionFromDataSource();
}
