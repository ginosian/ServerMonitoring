package listener;

import java.sql.Connection;

/**
 * Created by marta.ginosyan on 8/26/2016.
 */
/**Listens to db dropping occurrence.
 * Implementer is {@link db.DBConnection}. Notifier is {@link db.DBTables}*/
public interface DBConnectionListener {
    Connection unMapConnectionFromDataSource();
}
