package listener;

/**
 * Created by marta.ginosyan on 8/26/2016.
 */
/**Listens to db dropping occurrence.
 * Implementer is {@link db.DBConnection}. Notifier is {@link db.DBTables}*/
public interface DBConnectionListener {
    void unMapConnectionFromDataSource();
}
